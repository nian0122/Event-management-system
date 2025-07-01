package com.syr.service.impl;

import com.syr.mapper.ImageMapper;
import com.syr.pojo.ImageRecord;
import com.syr.service.ImageService;
import com.syr.utils.ImageCompressor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String GITHUB_API = "https://api.github.com/repos/{owner}/{repo}/contents/{path}";

    @Value("${github.token}")
    private String githubToken;

    @Value("${github.owner}")
    private String owner;

    @Value("${github.repo}")
    private String repo;

    @Value("${github.branch}")
    private String branch;
    @Value("${image.compression.quality}")
    private float compressionQuality;

    @Resource
    private ImageMapper imageMapper;

    @Override
    public String uploadToGitHub(MultipartFile file) throws IOException {

        byte[] compressedImage = ImageCompressor.compressImage(file, compressionQuality);

        String originalFilename = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);
        String fileName = UUID.randomUUID() + "." + fileExtension;
        String path = "images/" + fileName;

        String content = Base64.getEncoder().encodeToString(compressedImage);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Upload image");
        body.put("content", content);
        body.put("branch", branch);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(
                GITHUB_API,
                HttpMethod.PUT,
                new HttpEntity<>(body, headers),
                Map.class,
                owner, repo, path
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            String url = String.format("https://raw.githubusercontent.com/%s/%s/%s/%s",
                    owner, repo, branch, path);
            ImageRecord imageRecord = new ImageRecord(fileName, url, LocalDateTime.now());
            imageMapper.insertImage(imageRecord);
            return url;
        }
        throw new IOException("Failed to upload image to GitHub");
    }
}


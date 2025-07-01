package com.syr.controller;

import com.syr.pojo.Result;
import com.syr.service.ImageService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Resource
    private ImageService imageService;

    @PostMapping("/upload")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.uploadToGitHub(file);
            return Result.success(imageUrl);
        } catch (IOException e) {
            return Result.error("上传图片失败");
        }
    }
}

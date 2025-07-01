package com.syr.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String uploadToGitHub(MultipartFile file) throws IOException;
}

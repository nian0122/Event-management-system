package com.syr.controller;

import com.syr.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 获取当前工作目录
        String currentWorkingDir = System.getProperty("user.dir");
        // 构建相对路径
        String relativePath = "files/imgs/" + filename;
        // 构建保存文件的绝对路径
        String absolutePath = currentWorkingDir + "/" + relativePath;
        file.transferTo(new File(absolutePath));
        String url = "file:///" + absolutePath;
        return Result.success(url);
    }
}

package com.syr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageRecord {
    private Long id;

    private String fileName;

    private String filePath;

    private LocalDateTime uploadTime;

    public ImageRecord(String fileName, String url, LocalDateTime now) {
        this.fileName = fileName;
        this.filePath = url;
        this.uploadTime = now;
    }
}

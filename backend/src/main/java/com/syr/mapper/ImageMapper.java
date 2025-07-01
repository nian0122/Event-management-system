package com.syr.mapper;

import com.syr.pojo.ImageRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    @Insert("INSERT INTO image_record (file_name, file_path, upload_time) " +
            "VALUES (#{fileName}, #{filePath}, #{uploadTime})")
    void insertImage(ImageRecord record);
}

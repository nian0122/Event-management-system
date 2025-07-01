package com.syr.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageCompressor {

    /**
     * 压缩图片
     *
     * @param file    原始图片文件
     * @param quality 压缩质量，取值范围0.0f~1.0f
     * @return 压缩后的图片字节数组
     * @throws IOException
     */
    public static byte[] compressImage(MultipartFile file, float quality) throws IOException {
        // 读取图片
        BufferedImage image = ImageIO.read(file.getInputStream());

        // 创建输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 创建图片写入器，这里以JPEG格式为例
        ImageIO.write(image, "jpg", outputStream);

        // 获取原始图片字节数组
        byte[] data = outputStream.toByteArray();

        // 创建输入流
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

        // 创建图片读取器
        BufferedImage readImage = ImageIO.read(inputStream);

        // 创建一个新的输出流用于压缩后的图片
        ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream();

        // 使用ImageWriter进行图片压缩
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }

        // 写入压缩后的图片到输出流
        writer.setOutput(ImageIO.createImageOutputStream(compressedOutputStream));
        writer.write(null, new IIOImage(readImage, null, null), param);
        writer.dispose();

        // 返回压缩后的图片字节数组
        return compressedOutputStream.toByteArray();
    }
}

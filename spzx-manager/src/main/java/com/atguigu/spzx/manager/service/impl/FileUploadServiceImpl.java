package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.MinioProperties;
import io.minio.PutObjectArgs;
import jakarta.validation.constraints.Min;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileUploadServiceImpl implements FileUploadService {


    @SneakyThrows
    @Override
    public String fileUpload(MultipartFile file) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.222.20:9001")
                .credentials("admin", "admin123456")
                .build();

        PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket("spzx-1").
                object("avatar" + DateTime.now().toString("/YYYY/MM/dd/") + file.getOriginalFilename())
                .stream(file.getInputStream(), file.getSize(), -1).build();

        minioClient.putObject(putObjectArgs);

        return "http://192.168.222.20:9001" + "/spzx-1" + "/avatar" + DateTime.now().toString("/YYYY/MM/dd/") + file.getOriginalFilename();
    }
}

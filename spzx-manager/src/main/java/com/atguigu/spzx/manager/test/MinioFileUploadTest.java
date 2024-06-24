package com.atguigu.spzx.manager.test;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioFileUploadTest {
    public static void main(String[] args) throws Exception {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.222.20:9001")
                .credentials("admin", "admin123456")
                .build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("spzx-bucket").build());

        System.out.println("1");
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("spzx-bucket").build());
        } else {
            System.out.println("Bucket already exists");
        }
        System.out.println("2");


        FileInputStream file = new FileInputStream("C:\\Users\\dongs\\OneDrive\\图片\\头像\\1.png");
        PutObjectArgs putObjectArgs = new PutObjectArgs().builder()
                .bucket("spzx-bucket").stream(file,file.available(),-1).object("1.png" ).build();
        minioClient.putObject(putObjectArgs);



        System.out.println("3");

        String fileUrl = "http://192.168.222.20:9001/spzx-bucket/1.png";
        System.out.println(fileUrl);

    }
}

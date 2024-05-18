package com.chatai.demo.utils;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinioUploader {

    @Autowired
    private MinioConfiguration minioConfiguration;

    public void uploadFileIntoMinio(MultipartFile data, String filename)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeyException, ServerException,
            InsufficientDataException, ErrorResponseException,
            InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioConfiguration.getEndPoint())
                .credentials(minioConfiguration.getAccessKey(), minioConfiguration.getSecretKey())
                .build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfiguration.getBucketName()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfiguration.getBucketName()).build());
        }
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioConfiguration.getBucketName())
                .object(filename)
                .stream(data.getInputStream(), data.getSize(), -1)
                .contentType(data.getContentType())
                .build());
    }
}

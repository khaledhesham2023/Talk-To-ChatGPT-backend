package com.chatai.demo.utils;

import com.chatai.demo.model.AudioData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinioUploader {

    @Autowired
    private MinioConfiguration minioConfiguration;

    public void uploadFileIntoMinio(File data)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeyException, ServerException,
            InsufficientDataException, ErrorResponseException,
            InvalidResponseException, XmlParserException, InternalException {
        System.out.println("url is " + minioConfiguration.getAccessKey());
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioConfiguration.getEndPoint())
                .credentials(minioConfiguration.getAccessKey(), minioConfiguration.getSecretKey())
                .build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfiguration.getBucketName()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfiguration.getBucketName()).build());
        }
        minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket(minioConfiguration.getBucketName())
                .object(data.getName())
                .filename(data.getAbsolutePath())
                .build());
    }
}

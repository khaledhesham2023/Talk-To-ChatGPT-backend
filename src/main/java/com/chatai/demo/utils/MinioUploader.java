package com.chatai.demo.utils;

import com.chatai.demo.model.AudioData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
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
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000/")
                .credentials(minioConfiguration.getAccessKey(), minioConfiguration.getSecretKey())
                .build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("uploadedrecords").build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("uploadedrecords").build());
        }
        minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket("uploadedrecords")
                .object(data.getName())
                .filename(data.getAbsolutePath())
                .build());
        System.out.println(data.getName() + " is successfully uploaded on MinIO");
    }
}

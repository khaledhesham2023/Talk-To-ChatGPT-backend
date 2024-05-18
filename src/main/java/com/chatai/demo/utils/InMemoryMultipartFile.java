package com.chatai.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class InMemoryMultipartFile implements MultipartFile {
    private final String name;
    private final String originalFileName;
    private final String contentType;
    private final byte[] content;

    public InMemoryMultipartFile(String name, String originalFileName, String contentType, byte[] content) {
        this.name = name;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.content = content;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFileName;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return this.contentType.length() == 0;
    }

    @Override
    public long getSize() {
        return this.content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return this.content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try (FileOutputStream outputStream = new FileOutputStream(dest)) {
            outputStream.write(this.content);
        }
    }
}

package com.chatai.demo.model;

public class AnswerData {
    private byte[] file;
    private String filename;

    public AnswerData(byte[] file, String filename) {
        this.file = file;
        this.filename = filename;
    }

    public AnswerData() {
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFile() {
        return file;
    }

    public String getFilename() {
        return filename;
    }
}

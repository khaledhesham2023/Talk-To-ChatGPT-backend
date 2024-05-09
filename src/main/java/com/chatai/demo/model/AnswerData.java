package com.chatai.demo.model;

public class AnswerData {
    private byte[] file;

    public AnswerData(byte[] file) {
        this.file = file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public byte[] getFile() {
        return file;
    }
}

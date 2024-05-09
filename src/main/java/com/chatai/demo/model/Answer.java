package com.chatai.demo.model;

public class Answer {
    private byte[] fileBytes;
    private long requestId;

    public Answer(byte[] fileBytes, long requestId) {
        this.fileBytes = fileBytes;
        this.requestId = requestId;
    }

    public Answer() {
    }

    public long getRequestId() {
        return requestId;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }
}

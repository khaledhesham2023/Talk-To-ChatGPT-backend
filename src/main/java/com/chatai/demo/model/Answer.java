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

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }
}

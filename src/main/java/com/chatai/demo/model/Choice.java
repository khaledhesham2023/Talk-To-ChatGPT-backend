package com.chatai.demo.model;

public class Choice {
    private long index;
    private Message message;


    public Choice(long index, Message message) {
        this.index = index;
        this.message = message;
    }

    public Choice() {
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getIndex() {
        return index;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

}

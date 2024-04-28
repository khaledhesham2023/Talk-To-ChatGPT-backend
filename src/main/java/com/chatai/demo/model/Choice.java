package com.chatai.demo.model;

public class Choice {
    private long index;
    private Message message;
    private String logprobs;
    private String finish_reason;

    public Choice(long index, Message message,String logprobs,String finish_reason) {
        this.index = index;
        this.message = message;
        this.logprobs = logprobs;
        this.finish_reason = finish_reason;
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

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public String getLogprobs() {
        return logprobs;
    }
}

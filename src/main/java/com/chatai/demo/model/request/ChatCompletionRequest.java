package com.chatai.demo.model.request;

import com.chatai.demo.model.Message;

import java.util.ArrayList;

public class ChatCompletionRequest {
    private String model;
    private ArrayList<Message> messages;

    public ChatCompletionRequest(String model, ArrayList<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public ChatCompletionRequest() {
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

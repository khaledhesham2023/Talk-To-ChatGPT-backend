package com.chatai.demo.model.response;
import com.chatai.demo.model.Choice;
import com.chatai.demo.model.Usage;
import java.util.ArrayList;

public class ChatCompletionResponse {
    private String id;
    private String object;
    private String model;
    private ArrayList<Choice> choices;
    private Usage usage;

    public ChatCompletionResponse(String id, String object, String model, ArrayList<Choice> choices, Usage usage) {
        this.id = id;
        this.object = object;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
    }

    public ChatCompletionResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}
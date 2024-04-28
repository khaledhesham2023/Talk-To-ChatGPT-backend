package com.chatai.demo.model.response;

import com.chatai.demo.model.Choice;
import com.chatai.demo.model.Usage;

import java.util.ArrayList;

public class ChatCompletionResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private ArrayList<Choice> choices;
    private Usage usage;
    private String system_fingerprint;

    public ChatCompletionResponse(String id, String object, long created, String model, ArrayList<Choice> choices, Usage usage, String system_fingerprint) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
        this.system_fingerprint = system_fingerprint;
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

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
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

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

    public void setSystem_fingerprint(String system_fingerprint) {
        this.system_fingerprint = system_fingerprint;
    }
}

package com.chatai.demo.model;

public class Usage {
    private long prompt_tokens;
    private long completion_tokens;
    private long total_tokens;

    public Usage(long prompt_tokens, long completion_tokens, long total_tokens) {
        this.prompt_tokens = prompt_tokens;
        this.completion_tokens = completion_tokens;
        this.total_tokens = total_tokens;
    }

    public Usage() {
    }

    public void setCompletion_tokens(long completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public long getCompletion_tokens() {
        return completion_tokens;
    }

    public void setPrompt_tokens(long prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }

    public long getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setTotal_tokens(long total_tokens) {
        this.total_tokens = total_tokens;
    }

    public long getTotal_tokens() {
        return total_tokens;
    }
}

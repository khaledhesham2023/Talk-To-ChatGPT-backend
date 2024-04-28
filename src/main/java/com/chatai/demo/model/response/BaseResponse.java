package com.chatai.demo.model.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BaseResponse {
    private boolean status;
    private String message;

    public BaseResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

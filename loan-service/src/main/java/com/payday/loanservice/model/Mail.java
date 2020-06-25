package com.payday.loanservice.model;

import java.io.Serializable;

public class Mail implements Serializable {
    private String toEmail;
    private String message;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mail(String toEmail, String message) {
        this.toEmail = toEmail;
        this.message = message;
    }

    public Mail() {
    }
}

package com.message.app.springbootsmsapp;

public class SmsPOJO {

    private String to; //phone no.
    private String message; //message content

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }



}

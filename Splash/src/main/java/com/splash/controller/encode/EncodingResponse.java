package com.splash.controller.encode;

public class EncodingResponse {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EncodingResponse() {
    }

    public EncodingResponse(String text) {
        this.text = text;
    }
}

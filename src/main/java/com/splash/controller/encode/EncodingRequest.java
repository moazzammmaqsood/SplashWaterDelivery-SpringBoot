package com.splash.controller.encode;

import javax.validation.constraints.NotEmpty;

public class EncodingRequest {
    @NotEmpty(message = "Text must not be empty.")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EncodingRequest() {
    }

    public EncodingRequest(@NotEmpty(message = "Text must not be empty.") String text) {
        this.text = text;
    }
}

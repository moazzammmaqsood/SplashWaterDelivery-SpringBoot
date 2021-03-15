package com.splash.domain;

public class ErrorResponse {

    private String errorMessage;

    public String getMessage() {
        return errorMessage;
    }

    public ErrorResponse(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}

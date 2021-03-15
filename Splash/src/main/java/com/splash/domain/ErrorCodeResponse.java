package com.splash.domain;

public class ErrorCodeResponse extends ErrorResponse {

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public ErrorCodeResponse(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

}

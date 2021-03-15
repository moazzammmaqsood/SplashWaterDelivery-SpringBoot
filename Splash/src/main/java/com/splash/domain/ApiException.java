package com.splash.domain;

public class ApiException extends RuntimeException {

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public ApiException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, String errorMessage, Exception exception) {
        super(errorMessage, exception);
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, Exception exception) {
        super(exception);
    }
}

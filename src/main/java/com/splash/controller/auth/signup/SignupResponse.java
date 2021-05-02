package com.splash.controller.auth.signup;

import java.io.Serializable;

public class SignupResponse implements Serializable {
    private String token;

    public SignupResponse(String token) {
        this.token = token;
    }

    public SignupResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.splash.controller.auth.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {
    @Size(max = 250, message = "Max length for email can not exceed 250 characters.")
    @NotEmpty(message = "username must not be empty.")
    private String username;

    @NotEmpty(message = "Password must not be empty.")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(@Size(max = 250, message = "Max length for email can not exceed 250 characters.")  @NotEmpty(message = "Username must not be empty.") String username, @NotEmpty(message = "Password must not be empty.") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.splash.service;

import com.splash.controller.auth.login.LoginRequest;
import com.splash.controller.auth.login.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest login);
}

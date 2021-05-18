package com.splash.controller.auth.login;

import com.splash.common.ParameterizedAction;
import com.splash.controller.base.BaseController;
import com.splash.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;

    @PostMapping(
            value = "/api/v1/public/auth/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> v1Login(@Valid @RequestBody LoginRequest loginRequest) {

    	
        ParameterizedAction<LoginRequest, ResponseEntity<?>> v1LoginInternal = (request) -> {
      
            LoginResponse response = loginService.login(request);
      
            
            ResponseEntity<LoginResponse> responses= ResponseEntity.ok(response);

            
            return responses;
        };

        return execute(loginRequest, v1LoginInternal);
    }
}

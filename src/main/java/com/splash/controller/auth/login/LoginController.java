package com.splash.controller.auth.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splash.common.ParameterizedAction;
import com.splash.controller.base.BaseController;
import com.splash.domain.entity.AppParamsEntity;
import com.splash.entity.model.ErrorResponse;
import com.splash.enums.ErrorEnums;
import com.splash.repository.AppParamRepository;
import com.splash.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    @Autowired
    AppParamRepository apprepo;
    @GetMapping(
            value = "/api/v1/public/auth/contactus",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> contactus(){

        Optional<AppParamsEntity> appparam =   apprepo.findById("contactus");

        if(!appparam.isPresent()) {
            ErrorResponse error=new ErrorResponse(ErrorEnums.SERVER_ERROR.getResponseCode(), ErrorEnums.USER_NOT_FOUND.getError(),ErrorEnums.USER_NOT_FOUND.getErrorDescription());
            return ResponseEntity.status(HttpStatus.OK).body(asJsonString(error));

        }
        return ResponseEntity.status(HttpStatus.OK).body(appparam.get().getValue());

    }


    static String  asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }




}

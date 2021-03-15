package com.splash.controller.encode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EncodingController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(
            value = "/api/v1/public/encode",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity v1Encode(@Valid @RequestBody EncodingRequest requestModel){
        return ResponseEntity.ok(new EncodingResponse(passwordEncoder.encode(requestModel.getText())));
    }
}

//package com.splash.controller.vendor;
//
//
//import com.splash.common.BasicAction;
//import com.splash.domain.SuccessResponse;
//import com.splash.service.impl.RequestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//public class RequestController {
//
//
//    @Autowired
//    RequestService requestService;
//    @GetMapping(
//            value = "/api/v1/private/vendor/getclients",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<?> v1AddRequest(@Valid @RequestBody DeliveryRequest client, BindingResult bindingResult) {
//
//
//        BasicAction<ResponseEntity<?>> v1getclients = () -> {
//            requestService.addRequest();
//            return ResponseEntity.ok(new SuccessResponse("Successfully Added Request"));
//        };
//
//        return execute(v1getclients);
//    }
//
//}

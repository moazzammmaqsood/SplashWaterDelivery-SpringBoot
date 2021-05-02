package com.splash;

import com.splash.domain.ErrorCodeResponse;
import com.splash.domain.constants.ApiStatusCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Customize the response for BindException.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getAllErrors().stream().forEach(c->sb.append(c.getDefaultMessage()));

        ErrorCodeResponse errorCodeResponse
                = new ErrorCodeResponse(ApiStatusCodes.BAD_REQUEST, sb.toString());

        return ResponseEntity.badRequest().body(errorCodeResponse);
    }

}

package com.splash.controller.base;

import com.splash.common.BasicAction;
import com.splash.common.ParameterizedAction;
import com.splash.domain.ApiException;
import com.splash.domain.ErrorCodeResponse;
import com.splash.domain.ErrorResponse;
import com.splash.domain.constants.ApiStatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class BaseController {

    protected <T> ResponseEntity<?> execute(T request, ParameterizedAction<T, ResponseEntity<?>> function) {
        ResponseEntity<?> result;

        try {
            result = function.apply(request);
        } catch (ApiException e) {
            result = handleApiException(e);
        } catch (Exception e) {
            result = handleException(e);
        }

        return result;
    }

    protected <T> ResponseEntity<?> execute(BasicAction<ResponseEntity<?>> function) {
        ResponseEntity<?> result;

        try {
            result = function.apply();
        } catch (ApiException e) {
            result = handleApiException(e);
        } catch (Exception e) {
            result = handleException(e);
        }

        return result;
    }

    protected ResponseEntity<?> created(UriComponentsBuilder uriComponentsBuilder, String url, Long id) {
        UriComponents uriComponents = uriComponentsBuilder.path(url).buildAndExpand(id.toString());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    private ResponseEntity<?> handleApiException(ApiException exception) {
        ErrorCodeResponse errorCodeResponse;
        ResponseEntity<?> result;

        switch (exception.getErrorCode()) {
            case ApiStatusCodes.BAD_REQUEST:
                errorCodeResponse = new ErrorCodeResponse(exception.getErrorCode(), exception.getMessage());
                result = new ResponseEntity(errorCodeResponse, HttpStatus.BAD_REQUEST);
                break;
            case ApiStatusCodes.CONFLICT:
                errorCodeResponse = new ErrorCodeResponse(exception.getErrorCode(), exception.getMessage());
                result = new ResponseEntity(errorCodeResponse, HttpStatus.CONFLICT);
                break;
            case ApiStatusCodes.UNAUTHORIZED:
                errorCodeResponse = new ErrorCodeResponse(exception.getErrorCode(), exception.getMessage());
                result = new ResponseEntity(errorCodeResponse, HttpStatus.UNAUTHORIZED);
                break;
            case ApiStatusCodes.FORBIDDEN:
                errorCodeResponse = new ErrorCodeResponse(exception.getErrorCode(), exception.getMessage());
                result = new ResponseEntity(errorCodeResponse, HttpStatus.FORBIDDEN);
                break;
            case ApiStatusCodes.NOT_FOUND:
                result = new ResponseEntity(HttpStatus.NOT_FOUND);
                break;
            case ApiStatusCodes.TOO_MANY_REQUESTS:
                result = new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
                break;
            default:
                errorCodeResponse = new ErrorCodeResponse(exception.getErrorCode(), exception.getMessage());
                result = new ResponseEntity(errorCodeResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                break;
        }

        return result;
    }

    private ResponseEntity<?> handleException(Exception exception) {
        ErrorResponse errorResponse;
        ResponseEntity result;

        boolean exposeInternalError = true;

        if (exposeInternalError) {
            errorResponse = new ErrorResponse(exception.getMessage());
            result = new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            result = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}

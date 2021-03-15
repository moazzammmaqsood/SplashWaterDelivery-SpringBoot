package com.splash;

import com.splash.domain.ErrorCodeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class SecurityUtils {

    public static String[] publicEndpoints = new String[] { "/api/v1/public/**" };

    public static void buildErrorCodeResponse(HttpServletResponse response, int status, String errorMessage) throws IOException {
        ErrorCodeResponse errorCodeResponse = new ErrorCodeResponse(status, errorMessage);
        response.setStatus(status);
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorCodeResponse));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}

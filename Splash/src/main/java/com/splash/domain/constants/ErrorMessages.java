package com.splash.domain.constants;

public final class ErrorMessages {
    public static final String UNAUTHORIZED_USER_TYPE = "You do not have permission to add user wtih user type: %s";
    public static final String DUPLICATE_EMAIL_ADDRESS = "This email address is already associated with another account.";
    public static final String DUPLICATE_USERNAME = "This username is already associated with another account.";
    
    // Authorization - Login - Jwt Filters
    public static final String USERNAME_NOT_FOUND = "User: %s, not found.";
    public static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact your administrator.";
    public static final String BAD_CREDENTIALS = "Username or password is incorrect.";
    public static final String AUTHENTICATION_FAILED = "Authentication Failed.";
    public static final String INVALID_TOKEN  = "The access token provided is malformed, or invalid for other reasons. You may request a new access token and retry.";
    public static final String EXPIRED_TOKEN = "The access token provided is expired.";
    public static final String FORBIDDEN = "You do not have permission to access/modify this resource.";
    public static final String NO_AUTHORIZATION_HEADER = "No Authorization header found in request.";
    public static final String INVALID_AUTHORIZATION_TOKEN_SCHEME = "Authorization header is not bearer scheme format.";
}

package com.splash.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "splash-sign.jwt")
public class JwtConfiguration {
    private String secret;
    private long expiry;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public String getSecret() {
        return secret;
    }

    public long getExpiry() {
        return expiry;
    }
}

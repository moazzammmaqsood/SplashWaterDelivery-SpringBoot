package com.splash.infrastructure.security.jwt;

import com.splash.SecurityUtils;
import com.splash.common.configuration.JwtConfiguration;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.User;
import com.splash.domain.entity.UserEntity;
import com.splash.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtConfiguration jwtConfiguration;

    @Autowired
    UserRepository userRepository;

    private final String AUTHORIZATION_HEADER = "Authorization";
    private final String AUTHORIZATION_TOKEN_PREFIX = "Bearer";

    /**
     * Can be overridden in subclasses for custom filtering control,
     * returning {@code true} to avoid filtering of the given request.
     * <p>The default implementation always returns {@code false}.
     *
     * @param request current HTTP request
     * @return whether the given request should <i>not</i> be filtered
     * @throws ServletException in case of errors
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return Arrays.stream(SecurityUtils.publicEndpoints)
                .anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        processFilter(request, response, filterChain);
    }

    private void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader(AUTHORIZATION_HEADER);

        // if Request does not have value in Authorization header
        if(! StringUtils.hasText(headerValue)) {
            SecurityUtils.buildErrorCodeResponse(response, ApiStatusCodes.UNAUTHORIZED, ErrorMessages.NO_AUTHORIZATION_HEADER);
            return;
        }

        // Authorization header value does not start with Bearer
        if (! headerValue.startsWith(AUTHORIZATION_TOKEN_PREFIX)) {
            SecurityUtils.buildErrorCodeResponse(response, ApiStatusCodes.UNAUTHORIZED, ErrorMessages.INVALID_AUTHORIZATION_TOKEN_SCHEME);
            return;
        }

        String token = headerValue.substring(AUTHORIZATION_TOKEN_PREFIX.length()+1);

        try {
            String username = jwtUtils.extractUsername(token);

            Optional<UserEntity> optionalUserEntity= userRepository.findByusername(username);
           
            // If username fetched from token is not present in db.
            if(! optionalUserEntity.isPresent()) {
                SecurityUtils.buildErrorCodeResponse(response, ApiStatusCodes.UNAUTHORIZED, ErrorMessages.INVALID_TOKEN);
                return;
            }
            
            User usermodel= new User(optionalUserEntity.get()); 

            UserDetails user = usermodel;

            // If user is inactive in db
            if(! user.isEnabled()){
                SecurityUtils.buildErrorCodeResponse(response, ApiStatusCodes.UNAUTHORIZED, ErrorMessages.ACCOUNT_LOCKED);
                return;
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.emptyList());

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            SecurityUtils.buildErrorCodeResponse(response, ApiStatusCodes.UNAUTHORIZED, ErrorMessages.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            SecurityUtils.buildErrorCodeResponse(response, ApiStatusCodes.UNAUTHORIZED, ErrorMessages.INVALID_TOKEN);
        }
    }
}

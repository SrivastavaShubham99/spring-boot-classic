package com.example.springbackend.springbootrestapi.security;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.*;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    @Override
    public void commence(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        AuthenticationException authenticationException
        ) throws IOException,ServletException{
            httpServletResponse.
            sendError(HttpServletResponse.SC_UNAUTHORIZED,
             authenticationException.getMessage());

        }

}

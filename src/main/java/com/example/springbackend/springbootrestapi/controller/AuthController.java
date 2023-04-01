package com.example.springbackend.springbootrestapi.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import com.example.springbackend.springbootrestapi.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.springbackend.springbootrestapi.payloads.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserDetailsService userDetailsService;

    AuthController(AuthService authService, UserDetailsService userDetailsService){
        this.authService=authService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value={"/login","/signIn"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String response=authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setJwtToken(response);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return new ResponseEntity<String>(response,HttpStatus.CREATED);
    }



}

package com.example.springbackend.springbootrestapi.controller;

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

    private AuthService authService;

    AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping(value={"/login","/signIn"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response=authService.login(loginDto);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

}

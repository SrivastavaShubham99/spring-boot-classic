package com.example.springbackend.springbootrestapi.services;
import com.example.springbackend.springbootrestapi.payloads.*;



public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}

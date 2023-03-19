package com.example.springbackend.springbootrestapi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class JwtAuthResponse {
    String jwtToken;
    String authType="Bearer";
}

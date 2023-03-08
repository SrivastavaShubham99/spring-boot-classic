package com.example.springbackend.springbootrestapi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterDto {

    String email;
    String username;
    String name;
    String password;
    
}

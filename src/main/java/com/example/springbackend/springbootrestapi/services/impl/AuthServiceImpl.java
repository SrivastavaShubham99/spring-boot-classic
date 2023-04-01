package com.example.springbackend.springbootrestapi.services.impl;

import com.example.springbackend.springbootrestapi.services.*;
import com.example.springbackend.springbootrestapi.entity.User;
import com.example.springbackend.springbootrestapi.entity.Role;

import com.example.springbackend.springbootrestapi.exceptions.BlogApiException;
import com.example.springbackend.springbootrestapi.payloads.*;
import com.example.springbackend.springbootrestapi.repository.RoleRepository;
import com.example.springbackend.springbootrestapi.repository.UserRepository;
import com.example.springbackend.springbootrestapi.security.JwtTokenProvider;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManagerRes,
    UserRepository userRepository,
    RoleRepository roleRepository,
    PasswordEncoder passwordEncoder,
    JwtTokenProvider jwtTokenProvider
    ){
        this.authenticationManager=authenticationManagerRes;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.jwtTokenProvider=jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto){
        try{
            Authentication authentication=authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmailOrUsername(), loginDto.getPassword()));
            String token=jwtTokenProvider.generateToken(authentication);
            //we have to store the authentication  object into the securityContextHolder....
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return token;
        }catch (Exception e){
            throw new BlogApiException("user or password doesn't match", HttpStatus.BAD_REQUEST);

        }

    }


    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogApiException("email already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogApiException("username already exists", HttpStatus.BAD_REQUEST);
        }

        User user=new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles=new HashSet<Role>();
        Role userRole=roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "user registered successfully";
    }
    
}

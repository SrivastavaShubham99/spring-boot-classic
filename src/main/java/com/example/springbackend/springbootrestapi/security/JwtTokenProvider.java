package com.example.springbackend.springbootrestapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.springbackend.springbootrestapi.exceptions.BlogApiException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication,long id){

        String username = authentication.getName();
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() +  (1000 * 60 * 60 * 24));
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .claim("userId",id)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get username from Jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Object getUserId(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId");
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new BlogApiException("Invalid JWT token", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException ex) {
            throw new BlogApiException("Expired JWT token", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException ex) {
            throw new BlogApiException("Unsupported JWT token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException ex) {
            throw new BlogApiException("JWT claims string is empty.", HttpStatus.BAD_REQUEST);
        }
    }
}

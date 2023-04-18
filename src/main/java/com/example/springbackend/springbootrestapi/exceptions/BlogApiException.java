package com.example.springbackend.springbootrestapi.exceptions;
import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public BlogApiException(String message,HttpStatus status){
        this.httpStatus=status;
        this.message=message;
    }

    public BlogApiException(String message,HttpStatus status,String message1){
        super(message);
        this.httpStatus=status;
        this.message=message1;
    } 

    public String getMessage(){
        return this.message;
    }

    public HttpStatus getStatus(){
        return this.httpStatus;
    }

}

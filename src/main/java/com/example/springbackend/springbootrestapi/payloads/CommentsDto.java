package com.example.springbackend.springbootrestapi.payloads;

import lombok.Data;

@Data
public class CommentsDto {
    private long id;
    private String name;
    private String email;
    private String body;
}

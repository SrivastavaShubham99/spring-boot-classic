

package com.example.springbackend.springbootrestapi.payloads;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto{
    Long postId;
    @NotEmpty
    @Size(min=2,message="title should be atleast more than two characters!")
    String title;
    @NotEmpty
    @Size(min=10,message="title should be atleast more than ten characters!")
    String description;
    @NotNull
    @NotEmpty
    String content;
    int commentsCount;
    
}
package com.example.springbackend.springbootrestapi.services;
import com.example.springbackend.springbootrestapi.payloads.*;

public interface PostService {
    PostDto createPost(PostDto post,String token);
    PostResponse getAllPosts(int pageNo,int pageSize,String token);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void deletePost(long id);
    PostResponse getPostsByUserId(int pageNo,int pageSize,String token);
}

package com.example.springbackend.springbootrestapi.services;
import com.example.springbackend.springbootrestapi.payloads.*;

public interface PostService {
    PostDto createPost(PostDto post);
    PostResponse getAllPosts(int pageNo,int pageSize);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void deletePost(long id);
}

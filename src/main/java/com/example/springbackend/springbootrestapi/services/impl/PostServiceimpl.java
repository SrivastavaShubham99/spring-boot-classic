
package com.example.springbackend.springbootrestapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.springbackend.springbootrestapi.entity.Post;
import com.example.springbackend.springbootrestapi.exceptions.ResourceNotFoundException;
import com.example.springbackend.springbootrestapi.payloads.PostDto;
import com.example.springbackend.springbootrestapi.payloads.PostResponse;
import com.example.springbackend.springbootrestapi.repository.PostRepository;
import com.example.springbackend.springbootrestapi.services.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepository;
    @Autowired
    private CommentsService commentsService;

    PostServiceimpl(PostRepository postRepository,CommentsService commentsService) {
        this.postRepository = postRepository;
        this.commentsService=commentsService;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = DTOToPost(postDto);
        Post updatedPost = postRepository.save(post);
        PostDto postDto2 = postToDTO(updatedPost);
        return postDto2;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> postList = postRepository.findAll(pageable);
        List<Post> listPosts = postList.getContent();
        List<PostDto> content = listPosts.stream().map(ele -> postToDTO(ele)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setLast(postList.isLast());
        postResponse.setPageNo(postList.getNumber());
        postResponse.setPageSize(postList.getSize());
        postResponse.setTotalElements(content.size());
        postResponse.setTotalPages(postList.getTotalPages());
        return postResponse;

    }

    public PostDto postToDTO(Post post) {

        PostDto postDto = new PostDto();
        postDto.setPostId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setCommentsCount(commentsService.getCommentsByPostId(post.getId()).size());
        return postDto;
    }

    public Post DTOToPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setId(postDto.getPostId());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return post;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return postToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        Post updatedPost = postRepository.save(post);
        return postToDTO(updatedPost);

    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
        postRepository.delete(post);
    }
}

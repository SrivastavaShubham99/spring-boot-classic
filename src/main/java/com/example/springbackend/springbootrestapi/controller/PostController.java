package com.example.springbackend.springbootrestapi.controller;
import com.example.springbackend.springbootrestapi.security.JwtTokenProvider;
import com.example.springbackend.springbootrestapi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbackend.springbootrestapi.services.PostService;
import jakarta.validation.Valid;
import com.example.springbackend.springbootrestapi.payloads.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PostController.class);

    
    private final PostService postService;
    PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPosts(
            @Valid @RequestBody PostDto postDto,
             @RequestHeader(value="Authorization") String authorizationHeader
    ){
        String token =authorizationHeader.substring(7);
        return new ResponseEntity<PostDto>(postService.createPost(postDto,token),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
        @RequestParam(value="pageNo",defaultValue="0",required=false) int pageNo,
        @RequestParam(value="pageSize",defaultValue="0",required=false) int pageSize,
        @RequestHeader(value="Authorization") String authorizationHeader

    ){

        String token =authorizationHeader.substring(7);
        PostResponse postDtoList=postService.getAllPosts(pageNo,pageSize,token);
        return new ResponseEntity<PostResponse>(postDtoList,HttpStatus.OK);
    }


    @GetMapping("/self")
    public ResponseEntity<PostResponse> getAllPostsByUserId(
            @RequestParam(value="pageNo",defaultValue="0",required=false) int pageNo,
            @RequestParam(value="pageSize",defaultValue="0",required=false) int pageSize,
            @RequestHeader(value="Authorization") String authorizationHeader

    ){

        String token =authorizationHeader.substring(7);
        PostResponse postDtoList=postService.getPostsByUserId(pageNo,pageSize,token);
        return new ResponseEntity<PostResponse>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }




    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id")long id){
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")long id){
        postService.deletePost(id);
        return new ResponseEntity<String>("Post deleted successfully",HttpStatus.OK);
    }

}

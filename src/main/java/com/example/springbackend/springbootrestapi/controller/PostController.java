package com.example.springbackend.springbootrestapi.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springbackend.springbootrestapi.services.PostService;
import jakarta.validation.Valid;
import com.example.springbackend.springbootrestapi.payloads.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    private PostService postService;
    PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPosts(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
        @RequestParam(value="pageNo",defaultValue="0",required=false) int pageNo,
        @RequestParam(value="pageSize",defaultValue="0",required=false) int pageSize

    ){
        PostResponse postDtoList=postService.getAllPosts(pageNo,pageSize);
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

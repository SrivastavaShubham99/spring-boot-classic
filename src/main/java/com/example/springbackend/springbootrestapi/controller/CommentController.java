package com.example.springbackend.springbootrestapi.controller;

import java.util.*;
import java.util.logging.Logger;

import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbackend.springbootrestapi.payloads.*;
import com.example.springbackend.springbootrestapi.services.CommentsService;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentsService commentsService;

    CommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<CustomMessage> deleteCommentsById(@PathVariable("postId") long postId,
                                                     @PathVariable("commentId") long commentsId) {
        CustomMessage msg = commentsService.deleteCommentById(postId, commentsId);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("posts/{id}/comments")
    ResponseEntity<CommentsDto> postComments(@PathVariable("id") int id, @RequestBody CommentsDto commentsDto) {
        CommentsDto commentDto = commentsService.postComments(id, commentsDto);
        return new ResponseEntity<CommentsDto>(commentDto, HttpStatus.CREATED);
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentsDto> updateCommentsById(@RequestBody CommentsDto commentsDto,
                                                   @PathVariable("postId") long postId,
                                                   @PathVariable("commentId") long commentdId) {
        CommentsDto comments = commentsService.updateCommentById(postId, commentdId, commentsDto);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("posts/{postId}/comments/{commentId}")
    ResponseEntity<CommentsDto> getCommentsById(@PathVariable("postId") long postId,
                                                @PathVariable("commentId") long commentId) {
        CommentsDto comments = commentsService.getCommentById(postId, commentId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("posts/{id}/comments")
    List<CommentsDto> getCommentsByPostId(@PathVariable("id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("authentications details : " + authentication.getAuthorities());
        System.out.println("authentications details : " + authentication.getDetails());
        System.out.println("authentications details : " + authentication.getPrincipal());

        return commentsService.getCommentsByPostId(id);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

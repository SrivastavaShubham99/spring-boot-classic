package com.example.springbackend.springbootrestapi.services.impl;

import com.example.springbackend.springbootrestapi.services.*;
import java.util.List;
import java.util.stream.Collectors;
import com.example.springbackend.springbootrestapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.springbackend.springbootrestapi.entity.*;
import com.example.springbackend.springbootrestapi.payloads.*;

import com.example.springbackend.springbootrestapi.exceptions.ResourceNotFoundException;
import com.example.springbackend.springbootrestapi.payloads.CommentsDto;
import com.example.springbackend.springbootrestapi.repository.CommentsRepository;
import com.example.springbackend.springbootrestapi.repository.PostRepository;

@Service
public class CommentsServiceImpl implements CommentsService {

    private CommentsRepository commentsRepository;
    private PostRepository postRepository;

    CommentsServiceImpl(CommentsRepository commentsRepository, PostRepository pRepository) {
        this.commentsRepository = commentsRepository;
        this.postRepository = pRepository;
    }

    private CommentsDto mapToDto(Comments comments) {
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setBody(comments.getBody());
        commentsDto.setEmail(comments.getEmail());
        commentsDto.setId(comments.getId());
        commentsDto.setName(comments.getName());
        return commentsDto;
    }

    private Comments mapToComments(CommentsDto commentsDto) {
        Comments comments = new Comments();
        comments.setBody(commentsDto.getBody());
        comments.setEmail(commentsDto.getEmail());
        comments.setId(commentsDto.getId());
        comments.setName(commentsDto.getName());
        return comments;
    }

    @Override
    public CommentsDto postComments(long id, CommentsDto commentDto) {
        Comments comment = mapToComments(commentDto);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", id));
        comment.setPost(post);
        Comments updatedComments = commentsRepository.save(comment);
        return mapToDto(updatedComments);
    }

    @Override
    public List<CommentsDto> getCommentsByPostId(long id) {
        List<Comments> commentsList = commentsRepository.findByPostId(id);
        return commentsList.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public CommentsDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentId));
        if (!comments.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("comments doesn't belong to any posts", HttpStatus.BAD_REQUEST);
        }
        return mapToDto(comments);
    }

    @Override
    public CommentsDto updateCommentById(long postId, long commentId, CommentsDto commentsDto) {
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));
        if (!comments.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("comments doesn't belong to any posts", HttpStatus.BAD_REQUEST);
        }
        comments.setBody(commentsDto.getBody());
        comments.setEmail(commentsDto.getEmail());
        comments.setName(commentsDto.getName());
        Comments updatedComments = commentsRepository.save(comments);
        return mapToDto(updatedComments);
    }

    @Override
    public CustomMessage deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", "id", postId));
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentId));
        if (!comments.getPost().getId().equals(post.getId())) {
            throw new BlogApiException("comments doesn't belong to any posts", HttpStatus.BAD_REQUEST);
        }
        commentsRepository.delete(comments);
        return new CustomMessage("comment deleted successfully");
    }
}

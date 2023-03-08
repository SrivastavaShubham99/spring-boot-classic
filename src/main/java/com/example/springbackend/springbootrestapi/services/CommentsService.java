

package com.example.springbackend.springbootrestapi.services;
import com.example.springbackend.springbootrestapi.payloads.*;
import java.util.*;



public interface CommentsService{
    CommentsDto postComments(long id,CommentsDto commentDto);
    List<CommentsDto> getCommentsByPostId(long id);
    CommentsDto getCommentById(long postId,long commentId);
    CommentsDto updateCommentById(long postId,long commentId,CommentsDto commentsDto);
    CustomMessage deleteCommentById(long postId,long commentId);
}
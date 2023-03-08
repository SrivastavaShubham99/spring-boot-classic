

package com.example.springbackend.springbootrestapi.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbackend.springbootrestapi.entity.*;



public interface CommentsRepository extends JpaRepository<Comments,Long>{
    List<Comments> findByPostId(long postId);
} 
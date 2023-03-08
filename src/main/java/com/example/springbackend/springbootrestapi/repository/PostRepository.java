

package com.example.springbackend.springbootrestapi.repository;

import com.example.springbackend.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>{

}


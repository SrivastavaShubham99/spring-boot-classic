

package com.example.springbackend.springbootrestapi.repository;

import com.example.springbackend.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post,Long>{

    Optional<Page<Post>> findByUserId(long id,Pageable pageable);
}


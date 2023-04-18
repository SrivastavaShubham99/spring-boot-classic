package com.example.springbackend.springbootrestapi.repository;



import com.example.springbackend.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailOrUsername(String email,String username);
    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}

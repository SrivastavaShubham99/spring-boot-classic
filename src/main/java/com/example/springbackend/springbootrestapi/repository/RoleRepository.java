package com.example.springbackend.springbootrestapi.repository;

import com.example.springbackend.springbootrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}

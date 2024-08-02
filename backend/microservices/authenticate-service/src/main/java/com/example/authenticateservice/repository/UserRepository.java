package com.example.authenticateservice.repository;

import com.example.authenticateservice.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByUsername(String username);

    UsersEntity findByEmail(String email);
}

package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.model.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    UserToken findByTokenAndType(String token, UserToken.TokenType type);
    
    UserToken findByToken(String token);
}


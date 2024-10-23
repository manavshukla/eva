package com.example.security.service;

import com.example.security.model.User;
import com.example.security.model.UserToken;

public interface UserTokenService {

    public UserToken createToken(User user, UserToken.TokenType type);

    public boolean validateToken(String token, UserToken.TokenType type);
    
    public void deleteToken(UserToken token);
}

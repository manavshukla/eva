package com.example.security.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.security.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.UserToken;
import com.example.security.repository.UserTokenRepository;
import com.example.security.service.UserTokenService;

@Service
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserToken createToken(Shop shop, UserToken.TokenType type) {
    	UserToken token = new UserToken();
        token.setToken(UUID.randomUUID().toString());
        token.setShop(shop);
        //for validate and verification
        token.setExpiryDate(LocalDateTime.now().plusDays(1));
        token.setType(type);
        userTokenRepository.save(token);
        return token;
    }

    public boolean validateToken(String token, UserToken.TokenType type) {
    	UserToken tokenEntry = userTokenRepository.findByTokenAndType(token, type);
        if (tokenEntry != null && tokenEntry.getExpiryDate().isAfter(LocalDateTime.now())) {
            // Optionally, expire token after successful validation
            tokenEntry.setExpiryDate(LocalDateTime.now());
            userTokenRepository.save(tokenEntry);
            return true;
        }
        return false;
    }

	@Override
	public void deleteToken(UserToken token) {
		userTokenRepository.delete(token);
	}
}


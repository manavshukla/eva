package com.example.security.service;

import com.example.security.model.Shop;

import jakarta.mail.MessagingException;

public interface UserService {
	
	public Shop findByEmail(String email);

	public Shop enableUser(String token);

	Shop register(Shop newShop);

	void updatePassword(String token, String newPassword);

	void sendResetTokenEmail(String token, Shop shop) throws MessagingException;

	void sendVerificationEmail(String token, Shop shop) throws MessagingException;
	
	void deleteUser(Shop shop);

}

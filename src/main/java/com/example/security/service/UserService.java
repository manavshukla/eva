package com.example.security.service;

import com.example.security.model.User;

import jakarta.mail.MessagingException;

public interface UserService {
	
	public User findByEmail(String email);

	public User enableUser(String token);

	User register(User newUser);

	void updatePassword(String token, String newPassword);

	void sendResetTokenEmail(String token, User user) throws MessagingException;

	void sendVerificationEmail(String token, User user) throws MessagingException;
	
	void deleteUser(User user);

}

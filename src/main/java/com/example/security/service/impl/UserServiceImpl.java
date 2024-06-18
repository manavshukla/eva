package com.example.security.service.impl;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.model.User;
import com.example.security.model.UserToken;
import com.example.security.repository.UserRepository;
import com.example.security.repository.UserTokenRepository;
import com.example.security.service.EmailSenderService;
import com.example.security.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserTokenRepository userTokenRepository;
    
    @Autowired
    private EmailSenderService emailSenderService;
    
    @Value("${app.baseUrl}")
    private String baseUrl; 
    
    private final static String supportEmail = "marta.software.noreply@gmail.com";

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;

    private static SecureRandom random = new SecureRandom();

    public static String generateRandomPassword() {
        String password = "";
        for (int i = 0; i < 4; i++) {
            password += CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length()));
            password += CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length()));
            password += NUMBER.charAt(random.nextInt(NUMBER.length()));
            password += OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length()));
        }
        password = password.substring(0, 8 + random.nextInt(5)); // trim password to be 8 to 12 characters long
        return password;
    }
    
	@Override
	public User findByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		return userOptional.orElse(null);
	}
	
	@Override
	public User register(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setEnabled(false);
        return userRepository.save(newUser);
    }

	@Override
    public void updatePassword(String token, String newPassword) {
        UserToken resetToken = userTokenRepository.findByToken(token);
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User enableUser(String token) {
        UserToken verificationToken = userTokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    @Override
    @Async
    // Send an email to the user with a link to reset their password
    // the baseUrl should actually be a reset password form in the frontend
    // without a password, which then you can use to send a request with the same token and new-password to the backend
    public void sendResetTokenEmail(String token, User user) throws MessagingException {
        // Generate a random password for the user using and randomizer
        String newRandomPassword = generateRandomPassword();

    	String url = baseUrl + "/api/v1/auth/reset-password?token=" + token; //+ "&new-password=" + user.getEmail();
    	emailSenderService.sendEmail(user.getEmail(), 
    			"Password Reset Request", "To reset your password, click the link below:\n" + url);

    }

    @Override
    @Async
    public void sendVerificationEmail(String token, User user) throws MessagingException {
    	String url = baseUrl + "/api/v1/auth/verify-account?token=" + token;
    	emailSenderService.sendEmail(user.getEmail(), 
    			"Verify your account", "To verify your account, click the link below:\n" + url);
    }

	@Override
	public void deleteUser(User user) {
        user.getRoles().clear();
        userRepository.save(user);
		userRepository.delete(user);
		
	}

}

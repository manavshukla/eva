package com.example.security.service.impl;

import java.security.SecureRandom;
import java.util.Optional;

import com.example.security.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.model.UserToken;
import com.example.security.repository.ShopRepository;
import com.example.security.repository.UserTokenRepository;
import com.example.security.service.EmailSenderService;
import com.example.security.service.UserService;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private ShopRepository shopRepository;
	
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
	public Shop findByEmail(String email) {
		Optional<Shop> userOptional = shopRepository.findByEmail(email);
		return userOptional.orElse(null);
	}
	
	@Override
	public Shop register(Shop newShop) {
        newShop.setPassword(passwordEncoder.encode(newShop.getPassword()));
        newShop.setEnabled(false);
        return shopRepository.save(newShop);
    }

	@Override
    public void updatePassword(String token, String newPassword) {
        UserToken resetToken = userTokenRepository.findByToken(token);
        Shop shop = resetToken.getShop();
        shop.setPassword(passwordEncoder.encode(newPassword));
        shopRepository.save(shop);
    }

    @Override
    public Shop enableUser(String token) {
        UserToken verificationToken = userTokenRepository.findByToken(token);
        Shop shop = verificationToken.getShop();
        shop.setEnabled(true);
        shopRepository.save(shop);
        return shop;
    }

    @Override
    @Async
    // Send an email to the shop with a link to reset their password
    // the baseUrl should actually be a reset password form in the frontend
    // without a password, which then you can use to send a request with the same token and new-password to the backend
    public void sendResetTokenEmail(String token, Shop shop) throws MessagingException {
        // Generate a random password for the shop using and randomizer
        String newRandomPassword = generateRandomPassword();

    	String url = baseUrl + "/api/v1/auth/reset-password?token=" + token; //+ "&new-password=" + shop.getEmail();
    	emailSenderService.sendEmail(shop.getEmail(),
    			"Password Reset Request", "To reset your password, click the link below:\n" + url);

    }

    @Override
    @Async
    public void sendVerificationEmail(String token, Shop shop) throws MessagingException {
    	String url = baseUrl + "/api/v1/auth/verify-account?token=" + token;
    	emailSenderService.sendEmail(shop.getEmail(),
    			"Verify your account", "To verify your account, click the link below:\n" + url);
    }

	@Override
	public void deleteUser(Shop shop) {
        shop.getRoles().clear();
        shopRepository.save(shop);
		shopRepository.delete(shop);
		
	}

}

package com.example.security.controller;


import com.example.security.dto.PasswordResetRequest;
import com.example.security.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.auth.AuthenticationService;
import com.example.security.model.UserToken;
import com.example.security.payload.request.LoginRequest;
import com.example.security.payload.request.RegisterRequest;
import com.example.security.payload.response.JwtResponse;
import com.example.security.payload.response.MessageResponse;
import com.example.security.service.UserService;
import com.example.security.service.UserTokenService;

import jakarta.mail.MessagingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	@Autowired
    private AuthenticationService Authservice;
	
	@Autowired
	private UserService userService;
    
    @Autowired
    private UserTokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register (
            @RequestBody RegisterRequest request
    ) {
    	UserToken token = null;
    	Shop createdShop = null;

        String email = request.getEmail();
        String password = request.getPassword();

        // Check if email and password are not null
        if(email == null || password == null) {
            MessageResponse response = new MessageResponse("Email and password are required");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if email is valid
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.matches()) {
            MessageResponse response = new MessageResponse("Invalid email format");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if password is valid
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.matches()) {
            MessageResponse response = new MessageResponse("Password must be at least 6 characters long and contain at least one uppercase letter, one lowercase letter, and one number");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if shop exists
        if(userService.findByEmail(email) != null) {
            MessageResponse response = new MessageResponse("Shop already exists");
            return ResponseEntity.badRequest().body(response);
        }
        try{
        	JwtResponse response = Authservice.register(request);
        	createdShop = userService.findByEmail(response.getEmail());
        	token = tokenService.createToken(createdShop,
        			UserToken.TokenType.ACCOUNT_VERIFICATION);
        	userService.sendVerificationEmail(token.getToken(), createdShop);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
        	tokenService.deleteToken(token);
        	userService.deleteUser(createdShop);
        	MessageResponse errorResponse = new MessageResponse(e.getClass() +  e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (MessagingException e) {
            tokenService.deleteToken(token);
        	userService.deleteUser(userService.findByEmail(request.getEmail()));
        	MessageResponse errorResponse = new MessageResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
		}
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate (
            @RequestBody LoginRequest request
    ) {
        if(request.getEmail() == null || request.getPassword() == null) {
            MessageResponse response = new MessageResponse("Email and password are required");
            return ResponseEntity.badRequest().body(response);
        }
        if(userService.findByEmail(request.getEmail()) == null) {
            MessageResponse response = new MessageResponse("Shop not found");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            return ResponseEntity.ok(Authservice.authenticate(request));
        } catch (BadCredentialsException e) {
            MessageResponse response = new MessageResponse("Invalid email or password");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
    	UserToken token = null;
    	try {
    		Shop shop = userService.findByEmail(email);
    		if (shop != null) {
    			token = tokenService.createToken(shop, UserToken.TokenType.PASSWORD_RESET);
            
				userService.sendResetTokenEmail(token.getToken(), shop);
            return ResponseEntity.ok("Reset token sent to " + email);
    		}
    	} catch (Exception e) {
    		if(token != null)
    			tokenService.deleteToken(token);
    		return ResponseEntity.badRequest().body(e.getMessage());
		}
    	return ResponseEntity.badRequest().body("Email not found");
    }

    @PostMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestParam String token) {
        try {
            if (tokenService.validateToken(token, UserToken.TokenType.ACCOUNT_VERIFICATION)) {
                Shop shop = userService.enableUser(token);
                MessageResponse response = new MessageResponse("Account has been verified and activated");
                return ResponseEntity.ok().body(response);
            }
            MessageResponse response = new MessageResponse("Invalid or expired token");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            MessageResponse response = new MessageResponse(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody PasswordResetRequest request) {
        try {
            if (tokenService.validateToken(token, UserToken.TokenType.PASSWORD_RESET)) {
                userService.updatePassword(token, request.getNewPassword());
                MessageResponse response = new MessageResponse("Password has been reset successfully");
                return ResponseEntity.ok(response);
            }
            MessageResponse response = new MessageResponse("Invalid or expired token");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            MessageResponse response = new MessageResponse(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    

}
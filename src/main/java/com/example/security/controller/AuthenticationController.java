package com.example.security.controller;


import com.example.security.dto.PasswordResetRequest;
import com.example.security.exception.AuthenticationException;
import com.example.security.exception.RegistrationException;
import com.example.security.exception.ShopException;
import com.example.security.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class AuthenticationController {

    @Autowired
    private AuthenticationService Authservice;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        UserToken token = null;
        User createdUser = null;

        String email = request.getEmail();
        String password = request.getPassword();

        // Check if email and password are not null
        if (email == null || password == null) {
            throw new AuthenticationException("Email and password are required");
        }

        // Check if email is valid
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) {
            throw new AuthenticationException(" Invalid email format");
        }

        // Check if password is valid
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.matches()) {
            throw new AuthenticationException("Password must be at least 5 characters long and contain at least one uppercase letter, one lowercase letter, and one number");
        }

        // Check if shop exists
        // AB
        if (userService.findByEmail(email) != null) {
            throw new AuthenticationException("Shop already exists");
        }
        try {
            JwtResponse response = Authservice.register(request);
            createdUser = userService.findByEmail(response.getEmail());
            token = tokenService.createToken(createdUser,
                    UserToken.TokenType.ACCOUNT_VERIFICATION);
            userService.sendVerificationEmail(token.getToken(), createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            tokenService.deleteToken(token);
            userService.deleteUser(createdUser);
            throw new RegistrationException("Error while registration, Error :" + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody LoginRequest request
    ) {
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new AuthenticationException("Email and password are required");
        }
        if (userService.findByEmail(request.getEmail()) == null) {
            throw new ShopException("Shop not found");
        }

        try {
            return ResponseEntity.ok(Authservice.authenticate(request));
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Invalid email or password");
        }
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam @Email(message = "Invalid email format") String email) {
        UserToken token = null;
        try {
            User user = userService.findByEmail(email);
            if (user != null) {
                token = tokenService.createToken(user, UserToken.TokenType.PASSWORD_RESET);

                userService.sendResetTokenEmail(token.getToken(), user);
                return ResponseEntity.ok("Reset token sent to " + email);
            }
        } catch (Exception e) {
            if (token != null)
                tokenService.deleteToken(token);
            throw new AuthenticationException("Error while either creating token or sending token email to user, Error: " + e.getMessage());
        }
        throw new AuthenticationException("Email not found");
    }

    @PostMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestParam String token) {
        try {
            if (tokenService.validateToken(token, UserToken.TokenType.ACCOUNT_VERIFICATION)) {
                User user = userService.enableUser(token);
                MessageResponse response = new MessageResponse("Account has been verified and activated");
                return ResponseEntity.ok().body(response);
            }
            throw new AuthenticationException("Invalid or expired token");
        } catch (Exception e) {
            throw new AuthenticationException("Error while validating token, Error: " + e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody @Valid PasswordResetRequest request) {
        try {
            if (tokenService.validateToken(token, UserToken.TokenType.PASSWORD_RESET)) {
                userService.updatePassword(token, request.getNewPassword());
                MessageResponse response = new MessageResponse("Password has been reset successfully");
                return ResponseEntity.ok(response);
            }
            throw new AuthenticationException("Invalid or expired token");
        } catch (Exception e) {
            throw new AuthenticationException("Error while reset the password, Error: " + e.getMessage());
        }
    }
}
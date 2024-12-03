package com.example.security.service.impl;

import com.example.security.auth.jwt.JwtService;
import com.example.security.exception.CashierException;
import com.example.security.exception.EmailException;
import com.example.security.model.Cashier;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.model.UserRole;
import com.example.security.model.UserToken;
import com.example.security.model.UserToken.TokenType;
import com.example.security.payload.request.CashierRequest;
import com.example.security.repository.CashierRepository;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.service.CashierService;
import com.example.security.service.UserService;
import com.example.security.service.UserTokenService;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CashierServiceImpl implements CashierService {

    private final CashierRepository cashierRepository;

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserTokenService tokenService;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public void create(CashierRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CashierException("Email is already registered");
        }

        Role rp = roleRepository.findByName(UserRole.CASHIER).orElse(null);
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false)
                .locked(false)
                .roles(rp == null ? null : Set.of(rp))
                .build();

        userRepository.save(user);
        Cashier cashier = Cashier.builder().name(request.getName()).user(user).build();
        cashierRepository.save(cashier);

        UserToken token = tokenService.createToken(user,
                TokenType.ACCOUNT_VERIFICATION);
        try {
            userService.sendVerificationEmail(token.getToken(), user);
        } catch (Exception ex) {
            throw new EmailException("Error while sending verification email. Error: " + ex.getMessage());
        }
    }

    @Override
    public Page<Cashier> list(Pageable pageable) {
        return cashierRepository.findAll(pageable);
    }

}

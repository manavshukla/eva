package com.example.security.auth;

import com.example.security.auth.jwt.JwtService;
import com.example.security.model.Role;
import com.example.security.model.Shop;
import com.example.security.model.UserRole;
import com.example.security.payload.request.LoginRequest;
import com.example.security.payload.request.RegisterRequest;
import com.example.security.payload.response.JwtResponse;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ShopRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public JwtResponse register(RegisterRequest request) {
        Role rp = roleRepository.findByName(UserRole.USER).orElse(null);
        var user = Shop.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .corporateType(request.getCorporateType())
                .shopRegistrationData1(request.getShopRegistrationData1())
                .shopRegistrationData2(request.getShopRegistrationData2())
                .shopCity(request.getShopCity())
                .enabled(false)
                .locked(false)
                .shopName(request.getShopName())
                .shopAddress(request.getShopAddress())
                .roles(rp == null ? null : Set.of(rp))
                .build();

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }


        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        List<String> role = user.getRoles().stream()
                .map(r -> r.getName().name()).collect(Collectors.toList());

        return new JwtResponse(jwtToken,
                user.getId(),
                user.getEmail(),
                user.getEmail(),
                role);
    }

    public JwtResponse authenticate(LoginRequest request) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateJwtToken(authentication);

        Shop shopDetails = (Shop) authentication.getPrincipal();
        List<String> roles = shopDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                shopDetails.getId(),
                shopDetails.getUsername(),
                shopDetails.getEmail(),
                roles);
    }
}

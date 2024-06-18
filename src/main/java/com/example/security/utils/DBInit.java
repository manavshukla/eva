package com.example.security.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.security.model.Role;
import com.example.security.model.UserRole;
import com.example.security.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DBInit {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {


        List<Role> roles = roleRepository.findAll();


        if(!roles.stream().anyMatch(r -> r.getName().equals(UserRole.ADMIN))) {
            Role rAdmin = new Role(UserRole.ADMIN);
            roleRepository.save(rAdmin);
        }
        if (!roles.stream().anyMatch(r -> r.getName().equals(UserRole.USER))) {
            Role rUser = new Role(UserRole.USER);
            roleRepository.save(rUser);
        }


    }
}

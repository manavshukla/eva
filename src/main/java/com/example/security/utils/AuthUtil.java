package com.example.security.utils;

import com.example.security.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUtil {

    public String getLoggedInShopId() {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser == null) {
            throw  new RuntimeException("No logged in user found");
        }
        return loggedInUser.getShop();
    }

    public User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null
                || auth.getPrincipal() == null
                || !(auth.getPrincipal() instanceof User)) {
            return null;
        }
        return ((User) auth.getPrincipal());
    }

}

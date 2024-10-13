package com.example.security.utils;

import com.example.security.model.Shop;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthUtil {

    public Long getLoggedInShopId() {
        Shop loggedInUser = getLoggedInUser();
        if (loggedInUser == null) {
            throw  new RuntimeException("No logged in user found");
        }
        return loggedInUser.getId();
    }

    public Shop getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null
                || auth.getPrincipal() == null
                || !(auth.getPrincipal() instanceof Shop)) {
            return null;
        }
        return ((Shop) auth.getPrincipal());
    }

}

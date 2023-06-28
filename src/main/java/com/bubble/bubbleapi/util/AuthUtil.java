package com.bubble.bubbleapi.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthUtil {
    public UUID getUserIdFromSecurityContext() {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        } catch (Exception e) {
            return null;
        }
    }
}

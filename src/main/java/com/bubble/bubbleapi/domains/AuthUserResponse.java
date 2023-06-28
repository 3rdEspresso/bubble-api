package com.bubble.bubbleapi.domains;

import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserResponse {
    private User user;
    private String token;
}

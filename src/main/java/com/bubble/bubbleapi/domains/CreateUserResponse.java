package com.bubble.bubbleapi.domains;

import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserResponse {
    private Device device;
    private User user;
    private String token;
}

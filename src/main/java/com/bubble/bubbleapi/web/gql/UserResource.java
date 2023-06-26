package com.bubble.bubbleapi.web.gql;

import com.bubble.bubbleapi.configuration.JwtUtil;
import com.bubble.bubbleapi.domains.CreateUserResponse;
import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.entity.User;
import com.bubble.bubbleapi.service.DeviceService;
import com.bubble.bubbleapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class UserResource {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public Device deviceById(@Argument String id) {
        return deviceService.findDeviceById(id).orElse(null);
    }

    @MutationMapping
    public CreateUserResponse createUserWithDevice(@Argument User user, @Argument Device device) {
        Device savedDevice = deviceService.saveDevice(device);
        user.setPrimaryDevice(savedDevice);
        User savedUser = userService.saveUser(user);
        String token = jwtUtil.generateToken(savedUser.getUsername());

        return new CreateUserResponse(savedDevice, savedUser, token);
    }
}

package com.bubble.bubbleapi.web.gql;

import com.bubble.bubbleapi.util.AuthUtil;
import com.bubble.bubbleapi.util.JwtUtil;
import com.bubble.bubbleapi.domains.AuthUserResponse;
import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.entity.User;
import com.bubble.bubbleapi.service.DeviceService;
import com.bubble.bubbleapi.service.UserService;
import com.bubble.bubbleapi.web.gql.errors.UsernameAlreadyUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
public class UserResource {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthUtil authUtil;

    // --- QUERIES ---
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public Device deviceById(@Argument String id) {
        return deviceService.findDeviceById(id).orElse(null);
    }

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public User userById(@Argument UUID id) {
        return userService.findUserById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public AuthUserResponse refreshToken() {
        try {
            UUID currentUserId = authUtil.getUserIdFromSecurityContext();
            User user = userService.findUserById(currentUserId);
            String newToken = jwtUtil.generateToken(user.getId());
            return new AuthUserResponse(user, newToken);
        } catch (HttpStatusCodeException e) {
            throw new ResponseStatusException(e.getStatusCode());
        }
    }

    @QueryMapping
    public AuthUserResponse loginViaNfcCardAndDeviceId(@Argument String nfcCardId, @Argument String deviceId) {
        User user = userService.findUserByNfcCardId(nfcCardId);
        if (user == null || user.getPrimaryDevice() == null || !user.getPrimaryDevice().getId().equals(deviceId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(user.getId());
        return new AuthUserResponse(user, token);
    }

    // --- MUTATIONS ---
    @MutationMapping
    public AuthUserResponse createUserWithDevice(@Argument User user, @Argument Device device) {
        Device savedDevice = deviceService.saveDevice(device);
        try {
            user.setPrimaryDevice(savedDevice);
            User savedUser = userService.saveUser(user);
            String token = jwtUtil.generateToken(savedUser.getId());

            return new AuthUserResponse(savedUser, token);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameAlreadyUsedException();
        }
    }


    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public User registerNFCCard(@Argument String nfcCardId) {
        UUID currentUserId = authUtil.getUserIdFromSecurityContext();
        User user = userService.findUserById(currentUserId);

        // Check if nfcCardId is already registered with other user
        User existingUser = userService.findUserByNfcCardId(nfcCardId);
        if (existingUser != null) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.CONFLICT);
        }

        user.setNfcCardId(nfcCardId);
        return userService.saveUser(user);
    }
}

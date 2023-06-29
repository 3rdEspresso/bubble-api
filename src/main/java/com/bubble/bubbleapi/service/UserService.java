package com.bubble.bubbleapi.service;

import com.bubble.bubbleapi.domains.LoginCodeResponse;
import com.bubble.bubbleapi.entity.User;
import com.bubble.bubbleapi.repository.UserRepository;
import com.bubble.bubbleapi.util.AuthUtil;
import com.bubble.bubbleapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    private final StringRedisTemplate redisTemplate;
    private final ValueOperations<String, String> valueOperations;

    public UserService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public User findUserByNfcCardId(String nfcCardId) {
        return userRepository.findUserByNfcCardId(nfcCardId).orElse(null);
    }

    public String  generateAndSaveLoginCode(UUID userId) {
        String loginCode = generateHexCode(6);
        valueOperations.set("loginCode_" + loginCode, userId.toString(), 5, java.util.concurrent.TimeUnit.MINUTES);
        return loginCode;
    }

    public Optional<String> getUserIdViaLoginCode(String loginCode) {
        String userId = valueOperations.get("loginCode_" + loginCode);
        if (userId == null) {
            return Optional.empty();
        }
        valueOperations.getOperations().delete("loginCode_" + loginCode);
        return Optional.of(userId);
    }

    String generateHexCode(Integer n) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.substring(0, n);
    }
}

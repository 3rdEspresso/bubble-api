package com.bubble.bubbleapi.repository;

import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID uuid);

    Optional<User> findUserByPrimaryDevice(Device device);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByNfcCardId(String nfcCardId);
}

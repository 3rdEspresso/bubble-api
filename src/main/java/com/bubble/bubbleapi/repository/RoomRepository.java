package com.bubble.bubbleapi.repository;

import com.bubble.bubbleapi.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    List<Room> findAllByName(String name);

    Optional<Room> findRoomByNameAndType(String name, String type);
}

package com.bubble.bubbleapi.web.gql;

import com.bubble.bubbleapi.entity.Participant;
import com.bubble.bubbleapi.entity.Room;
import com.bubble.bubbleapi.entity.User;
import com.bubble.bubbleapi.service.RoomService;
import com.bubble.bubbleapi.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class RoomResource {
    @Autowired
    private RoomService roomService;
    @Autowired
    private AuthUtil authUtil;

    // --- QUERIES ---
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<Room> rooms() {
        UUID participantId = authUtil.getUserIdFromSecurityContext();
        return roomService.findRoomsByUserParticipation(participantId);
    }

    // --- MUTATIONS ---
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public Room createRoom(@Argument Room room, @Argument List<UUID> participantIds) {
        UUID participantId = authUtil.getUserIdFromSecurityContext();
        User createdBy = new User();
        createdBy.setId(participantId);
        room.setCreatedBy(createdBy);

        if (room.getType().equals("DM")) {
            if (participantIds.size() != 2) {
                throw new IllegalArgumentException("DMs must have exactly one other participant apart from yourself");
            }
        } else if (room.getType().equals("GROUP")) {
            if (participantIds.size() < 2) {
                throw new IllegalArgumentException("Groups must have at least two participants");
            }
        } else {
            throw new IllegalArgumentException("Invalid room type");
        }

        if (!participantIds.contains(participantId)) {
            participantIds.add(participantId);
        }
        return roomService.createRoomWithParticipants(room, participantIds);
    }

    // --- SCHEMA MAPPING ---
    @SchemaMapping
    public List<User> participants(Room room) {
        return roomService.findParticipantsByRoom(room.getId());
    }
}

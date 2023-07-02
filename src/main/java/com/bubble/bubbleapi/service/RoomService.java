package com.bubble.bubbleapi.service;

import com.bubble.bubbleapi.entity.Participant;
import com.bubble.bubbleapi.entity.Room;
import com.bubble.bubbleapi.entity.User;
import com.bubble.bubbleapi.repository.ParticipantRepository;
import com.bubble.bubbleapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public Room createRoomWithParticipants(Room room, List<UUID> participantIds) {
        Room createdRoom = roomRepository.save(room);
        participantIds.forEach(participantId -> {
            Participant participant = new Participant();
            User participantUser = new User();
            participantUser.setId(participantId);
            participant.setParticipant(participantUser);
            participant.setRoom(createdRoom);
            participantRepository.save(participant);
        });
        return createdRoom;
    }

    public List<Room> findRoomsByUserParticipation(UUID participantId) {
        List<Participant> participants = participantRepository.findAllByParticipant_Id(participantId);
        return participants.stream().map(Participant::getRoom).toList();
    }

    public List<User> findParticipantsByRoom(UUID roomId) {
        return participantRepository.findAllByRoom_Id(roomId).stream().map(Participant::getParticipant).toList();
    }

    public Participant findParticipantByRoomAndParticipant(UUID roomId, UUID participantId) {
        return participantRepository.findByRoom_IdAndParticipant_Id(roomId, participantId);
    }
}

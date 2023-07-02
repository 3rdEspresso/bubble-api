package com.bubble.bubbleapi.repository;

import com.bubble.bubbleapi.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    List<Participant> findAllByParticipant_Id(UUID participantId);

    List<Participant> findAllByRoom_Id(UUID roomId);

    Participant findByRoom_IdAndParticipant_Id(UUID roomId, UUID participantId);
}

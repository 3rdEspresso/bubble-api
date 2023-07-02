package com.bubble.bubbleapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private User participant;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
}

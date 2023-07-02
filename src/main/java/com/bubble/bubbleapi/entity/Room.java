package com.bubble.bubbleapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @OneToOne
    private User createdBy;
}

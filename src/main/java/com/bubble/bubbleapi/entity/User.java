package com.bubble.bubbleapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "nfc_card_id")
    private String nfcCardId;

    @OneToOne
    @JoinColumn(name = "primary_device_id", referencedColumnName = "id")
    private Device primaryDevice;
}

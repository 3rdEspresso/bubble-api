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

    // refer id from Device table as primaryDeviceId
    @OneToOne
    @JoinColumn(name = "primary_device_id", referencedColumnName = "id")
    private Device primaryDevice;
}

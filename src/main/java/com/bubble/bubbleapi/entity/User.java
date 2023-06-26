package com.bubble.bubbleapi.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "os")
    private String os;

    // refer id from Device table as primaryDeviceId
    @OneToOne
    @JoinColumn(name = "primary_device_id", referencedColumnName = "id")
    private Device primaryDevice;
}

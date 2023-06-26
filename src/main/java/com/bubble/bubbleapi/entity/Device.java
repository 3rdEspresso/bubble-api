package com.bubble.bubbleapi.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "os")
    private String os;

    @Column(name = "IMEI")
    private String IMEI;
}

package org.project.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "room_classification_id")
    private RoomClassification roomClassification;

    @Column(name = "price", nullable = false)
    private double price;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
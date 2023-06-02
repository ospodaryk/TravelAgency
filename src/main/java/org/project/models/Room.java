package org.project.models;

import lombok.Data;

import java.util.Set;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private int capacity;
    private int number;
    private String type;
    private double price;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany(mappedBy = "rooms")
    private Set<Booking> bookings;
}
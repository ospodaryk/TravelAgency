package org.project.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Min(value = 1, message = "Capacity should not be less than 1")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Min(value = 1, message = "Room number should not be less than 1")
    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "room_classification_id")
    private RoomClassification roomClassification;

    @Positive(message = "Price should be a positive value")
    @Column(name = "price", nullable = false)
    private double price;

    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @ColumnDefault("true")
    private boolean isActual;

    @Override
    public int hashCode() {
        return roomId != null ? roomId.hashCode() : 0;
    }


}
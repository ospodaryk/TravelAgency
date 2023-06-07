package org.project.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @NotNull(message = "Start date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date start_date;

    @NotNull(message = "End date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date end_date;

    @Min(value = 1, message = "Number of people should not be less than 1")
    private int numOfPeople;

    private boolean isActual;

    @Positive(message = "Price should be a positive value")
    @Column(name = "totalPrice", nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Room> rooms = new HashSet<>();
}

package org.project.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    private String name;
    private String location;
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
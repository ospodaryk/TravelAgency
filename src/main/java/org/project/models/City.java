package org.project.models;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
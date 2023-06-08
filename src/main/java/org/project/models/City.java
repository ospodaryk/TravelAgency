package org.project.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @NotBlank(message = "City name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private boolean isActual;

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private Set<Hotel> hotels = new HashSet<>();
}
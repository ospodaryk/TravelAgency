package org.project.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
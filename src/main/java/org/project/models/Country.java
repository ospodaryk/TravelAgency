package org.project.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;

    private String name;
}
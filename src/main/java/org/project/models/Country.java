package org.project.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;

    private String name;
}
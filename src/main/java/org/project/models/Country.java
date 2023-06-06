package org.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    private String name;


//    @OneToMany
//    @JoinColumn(name = "country_id")
//    private Set<City> cities;
}
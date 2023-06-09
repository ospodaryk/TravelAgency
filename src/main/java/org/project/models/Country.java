package org.project.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @NotBlank(message = "Country name cannot be blank")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "country",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();
    @Column(name = "isActual", columnDefinition = "true")
    private boolean isActual;
    @Override
    public int hashCode() {
        return countryId != null ? countryId.hashCode() : 0;
    }


}
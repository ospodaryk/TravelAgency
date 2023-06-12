package org.project.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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

    @OneToMany(mappedBy = "country",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<City> cities = new HashSet<>();
    @ColumnDefault("true")
    private boolean isActual;
    @Override
    public int hashCode() {
        return countryId != null ? countryId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                ", isActual=" + isActual +
                '}';
    }
}
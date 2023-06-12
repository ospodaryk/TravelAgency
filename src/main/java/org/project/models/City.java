package org.project.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Hotel> hotels = new HashSet<>();

    @ColumnDefault("true")
    private boolean isActual;

    @Override
    public int hashCode() {
        return cityId != null ? cityId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", isActual=" + isActual +
                '}';
    }
}
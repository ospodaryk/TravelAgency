package org.project.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @NotBlank(message = "Hotel name cannot be blank")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Location cannot be blank")
    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();
    @ColumnDefault("true")
    private boolean isActual;
    @Override
    public int hashCode() {
        return hotelId != null ? hotelId.hashCode() : 0;
    }


}
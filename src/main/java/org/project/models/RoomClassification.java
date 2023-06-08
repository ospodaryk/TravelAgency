package org.project.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "RoomClassification")
public class RoomClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Classification name cannot be blank")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "roomClassification",cascade = CascadeType.REMOVE)
    private Set<Room> rooms = new HashSet<>();
    private boolean isActual;

}
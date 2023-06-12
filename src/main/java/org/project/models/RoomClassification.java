package org.project.models;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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

    @OneToMany(mappedBy = "roomClassification", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();
    @ColumnDefault("true")
    private boolean isActual;
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RoomClassification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
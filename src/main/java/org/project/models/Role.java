package org.project.models;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Role name cannot be blank")
    @Column(name = "role_name", unique = true)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @ColumnDefault("true")
    private boolean isActual;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
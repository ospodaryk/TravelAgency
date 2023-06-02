package org.project.models;


import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String login;
    private String name;
    private String surname;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
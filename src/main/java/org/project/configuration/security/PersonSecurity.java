package org.project.configuration.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.project.models.Booking;
import org.project.models.Role;
import org.project.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@Getter
@Setter
public class PersonSecurity implements UserDetails {

    private long id;
    private Long userId;
    private String login;
    private String name;
    private String surname;
    private String email;
    private List<GrantedAuthority> authorities;
    private String password;
    private Set<Booking> mybookings ;

    public PersonSecurity(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.mybookings = user.getBookings();
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}


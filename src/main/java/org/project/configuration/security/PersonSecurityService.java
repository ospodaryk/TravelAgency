package org.project.configuration.security;

import org.project.dao.UserDAO;
import org.project.models.Booking;
import org.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class PersonSecurityService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public PersonSecurityService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with this email does not exist");
        }
        return new PersonSecurity(user);
    }
}

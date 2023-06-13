package org.project.configuration.security;

import org.project.dao.UserDAO;
import org.project.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final UserDAO userRepository;

    @Autowired
    public SecurityService(UserDAO userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("_____We are loadUserByUsername()");
        System.out.println("\n\n_____We are loadUserByUsername()\n\n");
        User user = userRepository.findByLogin(username);
        if (user == null) {
            logger.error("_____user==null");
            System.out.println("\n\n__________user==null\n\n");

            throw new UsernameNotFoundException("User with this email does not exists");
        }
        System.out.println("\n\n_______________Everything fine\n\n");

        logger.info("_____Everything fine");
        return new Security(user);
    }
}

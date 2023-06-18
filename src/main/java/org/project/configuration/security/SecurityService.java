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
        logger.info("Loading user by username: {}", username);
        User user = userRepository.findByLogin(username);
        if (user == null) {
            logger.error("User with username {} not found", username);
            throw new UsernameNotFoundException("User with this email does not exist");
        }
//        if (!user.isActual()) {
//            logger.error("User with username {} not found or was deleted", username);
//            throw new UsernameNotFoundException("User with this email does not exist or was deleted");
//        }
        logger.info("User {} loaded successfully", username);
        return new Security(user);
    }
}

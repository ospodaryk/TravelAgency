package org.project.service.implementation;

import org.project.dao.UserDAO;
import org.project.models.User;
import org.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(int userId) {
        LOGGER.info("Getting user by ID: {}", userId);
        return userDAO.findById(userId);
    }

    @Override
    public void saveUser(User user) {
        LOGGER.info("Saving user: {}", user);
        userDAO.save(user);
    }

    @Override
    public void updateUser(User user) {
        LOGGER.info("Updating user: {}", user);
        userDAO.update(user);
    }

    @Override
    public void deleteUser(User user) {
        LOGGER.info("Deleting user: {}", user);
        userDAO.delete(user);
    }
}
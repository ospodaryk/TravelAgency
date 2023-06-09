package org.project.service.implementation;

import org.project.dao.BookingDAO;
import org.project.dao.RoleDAO;
import org.project.dao.UserDAO;
import org.project.models.Role;
import org.project.models.User;
import org.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final BookingDAO bookingDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, BookingDAO bookingDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.bookingDAO = bookingDAO;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public User getUserById(long userId) {
        LOGGER.info("Getting user by ID: {}", userId);
        return userDAO.findById(userId);
    }

    @Override
    public void saveUser(User user) {
        LOGGER.info("Saving user: {}", user);
        userDAO.save(user);
    }

    @Override
    public void updateUser(long id, User user) {
        User existingUser = userDAO.findById(id);

        if (existingUser != null) {
            System.out.println("ROLE______________________________" + user.getRole());
            Role role = user.getRole();
            if (role != null) {
                existingUser.setRole(role);
            } else existingUser.setRole(roleDAO.findById(2L));

            existingUser.setUserId(id);
            existingUser.setName(user.getName());
            existingUser.setLogin(user.getLogin());
            existingUser.setPassword(user.getPassword());
            existingUser.setSurname(user.getSurname());
            existingUser.setEmail(user.getEmail());
            userDAO.update(existingUser);
        }

    }


    @Override
    public void deleteUser(long id) {
//        bookingDAO.deleteByUserId(id);
        userDAO.delete(userDAO.findById(id));
    }

    @Override
    public User findByLogin(String login) {
       return userDAO.findByLogin(login);
    }

}
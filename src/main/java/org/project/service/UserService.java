package org.project.service;

import org.project.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long userId);

    void saveUser(User user);

    void updateUser(long id, User user);

    void deleteUser(long id);
    User findByLogin(String login);

}

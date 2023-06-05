package org.project.service;

import org.project.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int userId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}

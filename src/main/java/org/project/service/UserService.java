package org.project.service;

import org.project.models.User;

public interface UserService {
    User getUserById(int userId);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}

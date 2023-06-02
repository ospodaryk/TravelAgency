package org.project.dao;

import org.project.models.User;

public interface UserDao {

    void save(User user);

    User get(long id);
}

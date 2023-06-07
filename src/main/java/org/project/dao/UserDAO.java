package org.project.dao;

import org.project.models.User;

public interface UserDAO extends GenericDAO<User, Long> {
    void deleteUserByRoleId(long id);
}
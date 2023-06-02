package org.project.dao;

import org.project.models.Role;

public interface RoleDao {

    void save(Role user);

    Role get(long id);
}

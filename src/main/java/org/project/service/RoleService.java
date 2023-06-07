package org.project.service;

import org.project.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(long id);

    void saveRole(Role role);

    void updateRole(long id, Role role);

    void deleteRole(long id);
}

package org.project.service;

import org.project.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(int id);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);
}

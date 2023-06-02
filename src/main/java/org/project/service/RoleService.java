package org.project.service;

import org.project.models.Role;

public interface RoleService {
    Role getRoleById(int id);
    void saveRole(Role role);
    void updateRole(Role role);
    void deleteRole(Role role);
}

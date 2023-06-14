package org.project.service.implementation;

import org.project.dao.RoleDAO;
import org.project.dao.UserDAO;
import org.project.models.Role;
import org.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;
    private final UserDAO userDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO, UserDAO userDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAll();
    }

    @Override
    public Role getRoleById(long id) {
        return roleDAO.findById(id);
    }

    @Override
    public void saveRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void updateRole(long id, Role role) {
        role.setId(id);
        roleDAO.update(role);
    }

    @Override
    public void deleteRole(long id) {
//        userDAO.deleteUserByRoleId(id);
        roleDAO.delete(roleDAO.findById(id));
    }
}
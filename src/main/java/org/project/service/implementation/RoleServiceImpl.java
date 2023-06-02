package org.project.service.implementation;

import org.project.dao.RoleDAO;
import org.project.models.Role;
import org.project.service.RoleService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;
    @Autowired

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleById(int id) {
        return roleDAO.findById(id);
    }

    @Override
    public void saveRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.update(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleDAO.delete(role);
    }
}
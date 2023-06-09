package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoleDAO;
import org.project.models.Role;
import org.springframework.stereotype.Repository;

@Repository

public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Role entity) {
//        Role role = findById(entity.getId());
//        if(role != null) {
//            if(role.getUsers().isEmpty()) {
        getSession().delete(entity);
//            } else {
//                throw new RuntimeException("Can't delete role with references to users.");
//            }
//        } else {
//            throw new RuntimeException("Role not found.");
//        }
    }

    // implementation of additional methods related to Country can be added here
}
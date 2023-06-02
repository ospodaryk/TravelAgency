package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoleDAO;
import org.project.models.Role;
import org.springframework.stereotype.Repository;

@Repository

public class RoleDAOImpl extends GenericDAOImpl<Role, Integer> implements RoleDAO {

    public RoleDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Country can be added here
}
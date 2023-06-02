package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoleDao;
import org.project.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Role user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Role get(long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    // Implement other methods for CRUD operations...
}

package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.UserDAO;
import org.project.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // implementation of additional methods related to Country can be added here
}
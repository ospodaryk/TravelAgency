package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.UserDAO;
import org.project.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public List<User> getUserWithThatRole(long roleId) {
        Session session = sessionFactory.getCurrentSession();

        String hqlUser = "FROM User WHERE role.id = :roleId";
        Query<User> queryUser = session.createQuery(hqlUser, User.class);
        queryUser.setParameter("roleId", roleId);

        return queryUser.getResultList();
    }

    public void deleteUserByRoleId(long roleId) {
        Session session = sessionFactory.getCurrentSession();

        List<User> users = getUserWithThatRole(roleId);
        String hqlBooking = "DELETE FROM Booking WHERE user.id = :userId";
        for (User user : users) {
            Query<?> queryBooking = session.createQuery(hqlBooking);
            queryBooking.setParameter("userId", user.getUserId());
            queryBooking.executeUpdate();
        }

        String hqlUser = "DELETE FROM User WHERE role.id = :roleId";
        Query<?> queryUser = session.createQuery(hqlUser);
        queryUser.setParameter("roleId", roleId);
        queryUser.executeUpdate();
    }
}

package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.dao.RoleDAO;
import org.project.models.Role;
import org.project.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.project.dao.CityDAO;
import org.project.dao.CountryDAO;
import org.project.models.City;
import org.project.models.Country;
import org.project.models.Hotel;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {
    private final SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Role entity) {
        Long roleId = entity.getId();
        Role role = findById(roleId);
        if (role != null) {
            boolean isRoleUsed = false;
            for (User user : role.getUsers()) {
                if (!user.getBookings().isEmpty()) {
                    isRoleUsed = true;
                    break;
                }
            }
            if (!isRoleUsed) {
                getSession().delete(entity);
            } else {
                role.setActual(false);
                for (User user : role.getUsers()) {
                   user.setActual(false);
                }
            }
        } else {
            throw new RuntimeException("Role not found.");
        }
    }
}
package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoleDAO;
import org.project.models.Role;
import org.project.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

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
            Set<User> usersToUpdate = new HashSet<>();
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
                usersToUpdate.addAll(role.getUsers()); // Store the users that need to be updated.
                for (User user : usersToUpdate) { // Iterate over the stored set of users.
                    user.setActual(false);
                }
            }
        } else {
            throw new RuntimeException("Role not found.");
        }
    }

}
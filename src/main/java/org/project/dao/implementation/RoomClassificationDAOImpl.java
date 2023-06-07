package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoleDAO;
import org.project.dao.RoomClassificationDAO;
import org.project.models.Role;
import org.project.models.RoomClassification;
import org.springframework.stereotype.Repository;

@Repository

public class RoomClassificationDAOImpl extends GenericDAOImpl<RoomClassification, Long> implements RoomClassificationDAO {

    public RoomClassificationDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
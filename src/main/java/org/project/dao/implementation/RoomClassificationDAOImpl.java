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

    @Override
    public void delete(RoomClassification entity) {
//        RoomClassification roomClassification = findById(entity.getId());
//        if(roomClassification != null) {
//            if(roomClassification.getRooms().isEmpty()) {
        getSession().delete(entity);
//            } else {
//                throw new RuntimeException("Can't delete room classification with references to rooms.");
//            }
//        } else {
//            throw new RuntimeException("Room Classification not found.");
//        }
    }
}
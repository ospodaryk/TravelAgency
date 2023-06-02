package org.project.dao.implementation;

import org.hibernate.SessionFactory;
import org.project.dao.RoomDao;
import org.project.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Room user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public Room get(long id) {
        return sessionFactory.getCurrentSession().get(Room.class, id);
    }

    // Implement other methods for CRUD operations...
}

package org.project.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.project.dao.GenericDAO;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;
    private SessionFactory sessionFactory;

    public GenericDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public T findById(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<T> getAll() {
        return getSession().createQuery("from " + getPersistentClass().getName(), getPersistentClass()).getResultList();
    }


    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }
}
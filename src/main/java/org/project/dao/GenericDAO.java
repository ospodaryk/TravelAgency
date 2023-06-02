package org.project.dao;

import java.io.Serializable;

public interface GenericDAO<T, ID extends Serializable> {
    T findById(ID id);

    void save(T entity);

    void delete(T entity);

    void update(T entity);
}

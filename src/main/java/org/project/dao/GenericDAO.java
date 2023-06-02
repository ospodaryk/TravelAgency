package org.project.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
    T findById(ID id);

    List<T> getAll();

    void save(T entity);

    void delete(T entity);

    void update(T entity);
}

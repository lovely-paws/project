package edu.johnshopkins.lovelypaws.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface Dao<T> {
    T findById(long id);
    T persist(T object);
    T merge(T object);
    Collection<T> findAll();
}

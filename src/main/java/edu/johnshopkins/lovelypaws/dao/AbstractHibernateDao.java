package edu.johnshopkins.lovelypaws.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

public abstract class AbstractHibernateDao<T> implements Dao<T> {

    @PersistenceContext
    protected EntityManager sessionFactory;

    private Class<T> clazz;
    public void setClazz(Class<T> clazz) { this.clazz = clazz; }

    @Override
    @Transactional
    public T findById(long id) {

        List<T> list = (List<T>) sessionFactory.createQuery("from "+clazz.getName()+" where id = :id")
                .setParameter("id", id)
                .getResultList();
        return (list.size() > 0) ? list.get(0) : null;
    }

    @Override
    @Transactional
    public T persist(T object) {
        sessionFactory.persist(object);
        return object;
    }

    @Override
    @Transactional
    public Collection<T> findAll() {
        return sessionFactory.createQuery("from "+clazz.getName()).getResultList();
    }
}

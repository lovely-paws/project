package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Application;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationDaoImpl extends AbstractHibernateDao<Application> implements ApplicationDao {
    public ApplicationDaoImpl() { clazz = Application.class; }
}

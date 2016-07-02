package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.springframework.stereotype.Repository;

@Repository
public class ShelterHibernateDaoImpl extends AbstractHibernateDao<Shelter> implements ShelterHibernateDao {

    public ShelterHibernateDaoImpl() {
        setClazz(Shelter.class);
    }
}

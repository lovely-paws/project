package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Shelter;

public interface ShelterHibernateDao extends Dao<Shelter> {
    Shelter findByName(String name);
}

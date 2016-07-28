package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AdoptionRequest;
import edu.johnshopkins.lovelypaws.entity.Shelter;

import java.util.List;

public interface ShelterHibernateDao extends Dao<Shelter> {
    Shelter findByName(String name);
    List<AdoptionRequest> getAdoptionRequests(long shelterId);
}

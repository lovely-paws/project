package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AdoptionRequest;
import org.springframework.stereotype.Repository;

@Repository
public class AdoptionRequestDaoImpl extends AbstractHibernateDao<AdoptionRequest> implements AdoptionRequestDao {
    public AdoptionRequestDaoImpl() { clazz = AdoptionRequest.class; }
}

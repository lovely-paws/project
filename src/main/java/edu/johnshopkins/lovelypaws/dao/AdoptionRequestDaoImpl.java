package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AdoptionRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdoptionRequestDaoImpl extends AbstractHibernateDao<AdoptionRequest> implements AdoptionRequestDao {
    public AdoptionRequestDaoImpl() { clazz = AdoptionRequest.class; }
}

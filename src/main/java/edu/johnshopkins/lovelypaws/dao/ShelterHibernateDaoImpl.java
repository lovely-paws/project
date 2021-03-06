package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AdoptionRequest;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ShelterHibernateDaoImpl extends AbstractHibernateDao<Shelter> implements ShelterHibernateDao {

    public ShelterHibernateDaoImpl() {
        clazz = (Shelter.class);
    }

    public Shelter findByName(String name) {
        name = StringUtils.upperCase(StringUtils.trimToNull(name));
        if(name == null) {
            return null;
        }

        List<Shelter> shelters = sessionFactory.createQuery("from "+Shelter.class.getSimpleName()+" where name=:name")
                .setParameter("name", name)
                .getResultList();

        return shelters.size() == 0 ? null : shelters.get(0);
    }

    public List<AdoptionRequest> getAdoptionRequests(long shelterId) {
        return sessionFactory.createQuery("from "+AdoptionRequest.class.getSimpleName()+" where listing.shelter.id=:shelterId")
                .setParameter("shelterId", shelterId)
                .getResultList();
    }
}

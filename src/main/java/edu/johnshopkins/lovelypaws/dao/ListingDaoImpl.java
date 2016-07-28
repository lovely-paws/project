package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Listing;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ListingDaoImpl extends AbstractHibernateDao<Listing> implements ListingDao {

    public ListingDaoImpl() { clazz = (Listing.class); }

    @Override
    @Transactional
    public List<Listing> findByIds(Collection<Long> ids) {
        List<Listing> listings = new ArrayList<>();
        for(Long id : ids) {
            listings.add(findById(id));
        }
        return listings;
    }

}

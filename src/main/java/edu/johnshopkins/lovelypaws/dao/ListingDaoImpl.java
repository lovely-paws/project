package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Listing;
import org.springframework.stereotype.Repository;

@Repository
public class ListingDaoImpl extends AbstractHibernateDao<Listing> implements ListingDao {

    public ListingDaoImpl() { setClazz(Listing.class); }

}

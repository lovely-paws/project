package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Listing;

import java.util.Collection;
import java.util.List;

public interface ListingDao extends Dao<Listing> {
    List<Listing> findByIds(Collection<Long> ids);
}

package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Listing;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.entity.enums.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListingBoImpl implements ListingBo {

    @Autowired
    private ListingDao listingDao;

    public Listing createListing(Shelter shelter, AnimalType animalType, String name, String description, Color color) {
        Listing listing = new Listing();
        listing.setAnimalType(animalType);
        listing.setName(name);
        listing.setDescription(description);
        listing.setColor(color);
        listing.setShelter(shelter);
        return listingDao.persist(listing);
    }
}

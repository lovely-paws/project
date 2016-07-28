package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Listing;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListingBoImpl implements ListingBo {

    @Autowired
    private ListingDao listingDao;

    public Listing createListing(Shelter shelter, AnimalType animalType, String name, String description, String color) {
        Listing listing = new Listing();
        if(animalType == null) {
            throw new IllegalArgumentException("animalType");
        }
        listing.setAnimalType(animalType);
        listing.setName(name);
        listing.setDescription(description);
        listing.setColor(color);
        listing.setShelter(shelter);
        listing.setVisible(true);
        return listingDao.persist(listing);
    }
}

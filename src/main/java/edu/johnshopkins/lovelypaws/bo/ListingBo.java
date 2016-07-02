package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Listing;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.entity.enums.Color;

public interface ListingBo {

    Listing createListing(Shelter shelter, AnimalType animalType, String name, String description, Color color);

}
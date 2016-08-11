package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.Age;
import edu.johnshopkins.lovelypaws.Gender;
import edu.johnshopkins.lovelypaws.beans.ListingInfo;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Listing;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.entity.User;

import java.io.File;

public interface ListingBo {

    ServerResponse<Listing> update(User user, ListingInfo listingInfo);
    Listing createListing(Shelter shelter, AnimalType animalType, String name, String description, String color, Gender gender, Age age, File imageFile);

}

package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.Age;
import edu.johnshopkins.lovelypaws.Gender;
import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.ListingInfo;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Listing;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import static edu.johnshopkins.lovelypaws.UserInputUtils.trimAndUpper;

@Component
public class ListingBoImpl implements ListingBo {

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private AnimalTypeDao animalTypeDao;

    public ServerResponse<Listing> createListing(Shelter shelter, AnimalType animalType, String name, String description, String color,
                                 Gender gender, Age age, File imageFile) {
        Listing listing = new Listing();
        if(animalType == null) {
            return new ServerResponse<>(false, "An animal type must be provided.", null);
        }
        listing.setAnimalType(animalType);

        name = trimAndUpper(name);
        if(name == null) {
            return new ServerResponse<>(false, "The listing must include a name.", null);
        }
        listing.setName(name);

        description = trimAndUpper(description);
        if(description == null) {
            return new ServerResponse<>(false, "The listing must include a description.", null);
        }
        listing.setDescription(description);

        color = trimAndUpper(color);
        if(color == null) {
            return new ServerResponse<>(false, "The listing must include a color.", null);
        }
        listing.setColor(color);

        if(shelter == null) {
            return new ServerResponse<>(false, "A shelter was not provided.", null);
        }
        listing.setShelter(shelter);

        listing.setVisible(true);

        if(gender == null) {
            return new ServerResponse<>(false, "A gender must be provided.", null);
        }
        listing.setGender(gender);

        if(age == null) {
            return new ServerResponse<>(false, "An age must be provided.", null);
        }
        listing.setAge(age);

        listing.setImageFile(imageFile);
        return new ServerResponse<>(true, "Listing created!", listingDao.persist(listing));
    }

    @Transactional
    public ServerResponse<Listing> update(User user, ListingInfo listingInfo) {
        if(user == null) {
            return new ServerResponse<>(false, "You must be logged in.", null);
        } else if(listingInfo == null) {
            return new ServerResponse<>(false, "Empty request body.", null);
        }

        Listing listing = listingDao.findById(listingInfo.getId());
        if(listing == null) {
            return new ServerResponse<>(false, "No such listing.", null);
        } else if(user.getRole() != Role.ADMINISTRATOR && listing.getShelter().getId() != user.getId()) {
            return new ServerResponse<>(false, "You are not authorized to update this listing.", null);
        }

        String name = StringUtils.upperCase(StringUtils.trimToEmpty(listingInfo.getName()));
        String color  = StringUtils.upperCase(StringUtils.trimToEmpty(listingInfo.getColor()));
        String description = StringUtils.upperCase(StringUtils.trimToEmpty(listingInfo.getDescription()));
        Age age = listingInfo.getAge();
        Gender gender = listingInfo.getGender();
        AnimalType animalType = animalTypeDao.findById(listingInfo.getAnimalTypeId());

        if(0 < name.length() && name.length() <= 32) {
            listing.setName(name);
        } else {
            return new ServerResponse<>(false, "Invalid name.", null);
        }

        if(0 < color.length() && color.length() <= 32) {
            listing.setColor(color);
        } else {
            return new ServerResponse<>(false, "Invalid color.", null);
        }

        if(0 < description.length() && description.length() <= 255) {
            listing.setDescription(description);
        } else {
            return new ServerResponse<>(false, "Invalid description.", null);
        }

        if(age != null) {
            listing.setAge(age);
        } else {
            return new ServerResponse<>(false, "Invalid age.", null);
        }

        if(gender != null) {
            listing.setGender(gender);
        } else {
            return new ServerResponse<>(false, "Invalid gender.", null);
        }

        if(animalType != null) {
            listing.setAnimalType(animalType);
        } else {
            return new ServerResponse<>(false, "Invalid animal type.", null);
        }

        return new ServerResponse<>(true, "Listing updated.", listingDao.merge(listing));
    }
}

package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.AddressInfo;
import edu.johnshopkins.lovelypaws.entity.*;

import java.util.List;

/** Business logic associated with Shelter instances. */
public interface ShelterBo {

    /** Tries to create a new Shelter instance using the provided information; if successful, the Shelter instance is added to the database. */
    Shelter createShelter(String username, String password, String name, String description, AddressInfo address, String phoneNumber, String emailAddress, List<AnimalType> animalTypes);

    /** Retrieve an existing Shelter instance from the database by ID. */
    Shelter getShelter(long id);

    void addListing(Shelter shelter, Listing listing);

    List<Shelter> getAllShelters();
    List<AdoptionRequest> getAdoptionRequests(Shelter shelter);

    boolean setShelterApprovedStatus(long shelterId, boolean approvedStatus);
    boolean isValidDescription(String description);
    boolean isValidPhoneNumber(String phoneNumber);
    boolean isValidAddressInfo(AddressInfo addressInfo);

}
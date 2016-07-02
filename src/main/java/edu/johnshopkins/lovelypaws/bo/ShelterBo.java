package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.entity.Address;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Shelter;

import java.util.List;

/** Business logic associated with Shelter instances. */
public interface ShelterBo {

    /** Tries to create a new Shelter instance using the provided information; if successful, the Shelter instance is added to the database. */
    Shelter createShelter(String username, String passwordSha512, String name, String description, Address address, String phoneNumber, List<AnimalType> animalTypes);

    /** Retrieve an existing Shelter instance from the database by ID. */
    Shelter getShelter(long id);

    List<Shelter> getAllShelters();
}
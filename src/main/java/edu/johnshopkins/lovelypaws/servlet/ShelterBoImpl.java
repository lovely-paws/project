package edu.johnshopkins.lovelypaws.servlet;

import edu.johnshopkins.lovelypaws.entity.Address;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShelterBoImpl implements ShelterBo {
    public Shelter createShelter(String username, String passwordSha512, String name, String description, Address address, String phoneNumber, List<AnimalType> animalTypes) {
        Shelter shelter = new Shelter();
        shelter.setUsername(username);
        shelter.setPasswordSha512(passwordSha512);
        shelter.setName(name);
        shelter.setDescription(description);
        shelter.setAddress(address);
        shelter.setPhoneNumber(phoneNumber);
        if(animalTypes != null) {
            shelter.getAnimalTypes().addAll(animalTypes);
        }

        return shelter;

    }
}

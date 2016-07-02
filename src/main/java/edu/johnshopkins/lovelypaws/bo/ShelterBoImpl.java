package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.ShelterHibernateDao;
import edu.johnshopkins.lovelypaws.entity.Address;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShelterBoImpl implements ShelterBo {

    @Autowired
    private ShelterHibernateDao shelterDao;

    public Shelter createShelter(String username, String passwordSha512, String name, String description, Address address, String phoneNumber, List<AnimalType> animalTypes) {
        // TODO: Check inputs and throw exceptions or return errors as appropriate.
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
        return shelterDao.persist(shelter);
    }

    public Shelter getShelter(long id) { return shelterDao.findById(id); }
}

package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.ShelterHibernateDao;
import edu.johnshopkins.lovelypaws.entity.Address;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import edu.johnshopkins.lovelypaws.entity.Listing;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShelterBoImpl implements ShelterBo {

    @Autowired
    private ShelterHibernateDao shelterDao;

    @PostConstruct
    private void init() {
        if(shelterDao.findByName("MUTT'S") == null) {
            Shelter shelter = new Shelter();
            shelter.setUsername("mutts");
            shelter.setPasswordSha512(DigestUtils.sha512Hex("mutts"));
            shelter.setEmailAddress("mutts@example.com");
            shelter.setName("MUTT'S");
            shelter.setDescription("Mutt's - A Place for Pups");
            shelter.setPhoneNumber("1-800-MUT-TPUP");

            Address address = new Address();
            address.setLine1("123 Main Street");
            address.setCity("Anytown");
            address.setState("IL");
            address.setZip("60652");
            shelter.setAddress(address);

            shelterDao.persist(shelter);
        }
    }

    public Shelter createShelter(String username, String passwordSha512, String name, String description, Address address, String phoneNumber, List<AnimalType> animalTypes) {
        username = StringUtils.upperCase(StringUtils.trimToNull(username));
        if(username == null) {
            throw new IllegalArgumentException("username");
        }

        // TODO: Check inputs and throw exceptions or return errors as appropriate.
        Shelter shelter = new Shelter();
        shelter.setUsername(username);
        shelter.setPasswordSha512(passwordSha512);
        shelter.setName(name);
        shelter.setDescription(description);
        shelter.setAddress(address);
        shelter.setPhoneNumber(phoneNumber);
        return shelterDao.persist(shelter);
    }

    public void addListing(Shelter shelter, Listing listing) {
        shelter.getListings().add(listing);
    }

    public Shelter getShelter(long id) { return shelterDao.findById(id); }

    public List<Shelter> getAllShelters() { return new ArrayList<>(shelterDao.findAll()); }
}

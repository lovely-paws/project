package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.ShelterHibernateDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShelterBoImpl implements ShelterBo {

    @Autowired
    private ShelterHibernateDao shelterDao;

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
        shelter.setApproved(false);
        return shelterDao.persist(shelter);
    }

    public void addListing(Shelter shelter, Listing listing) {
        shelter.getListings().add(listing);
    }

    public Shelter getShelter(long id) { return shelterDao.findById(id); }

    public List<Shelter> getAllShelters() { return new ArrayList<>(shelterDao.findAll()); }

    @Transactional
    public List<AdoptionRequest> getAdoptionRequests(Shelter shelter) {
        return shelterDao.getAdoptionRequests(shelter.getId());
    }
}

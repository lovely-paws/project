package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.AddressData;
import edu.johnshopkins.lovelypaws.beans.AddressInfo;
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

    public Shelter createShelter(String username, String passwordSha512, String name, String description, AddressData addressData, String phoneNumber, String emailAddress,  List<AnimalType> animalTypes) {
        username = StringUtils.upperCase(StringUtils.trimToNull(username));
        if(username == null) {
            throw new IllegalArgumentException("username");
        }

        // TODO: Check inputs and throw exceptions or return errors as appropriate.

        Address address = new Address();
        address.setLine1(addressData.getLine1());
        address.setLine2(addressData.getLine2());
        address.setCity(addressData.getCity());
        address.setState(addressData.getState());
        address.setZip(addressData.getZip());

        Shelter shelter = new Shelter();
        shelter.setUsername(username);
        shelter.setPasswordSha512(passwordSha512);
        shelter.setEmailAddress(emailAddress);
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

    @Transactional
    public boolean setShelterApprovedStatus(long shelterId, boolean approvedStatus) {
        Shelter shelter = shelterDao.findById(shelterId);
        if(shelter != null) {
            shelter.setApproved(approvedStatus);
            shelterDao.merge(shelter);
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidDescription(String description) {
        int length = StringUtils.upperCase(StringUtils.trimToEmpty(description)).length();
        return 0 < length && length < 255;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        int length = StringUtils.upperCase(StringUtils.trimToEmpty(phoneNumber)).length();
        return 0 < length && length < 32;
    }

    public boolean isValidAddressInfo(AddressInfo addressInfo) {
        if(addressInfo == null) {
            return false;
        }

        return StringUtils.length(addressInfo.getLine1()) > 0 &&
                StringUtils.length(addressInfo.getCity()) > 0 &&
                StringUtils.length(addressInfo.getState()) > 0 &&
                StringUtils.length(addressInfo.getZip()) > 0;
    }
}

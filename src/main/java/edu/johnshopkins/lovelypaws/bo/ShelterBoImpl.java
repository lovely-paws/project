package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.UserInputUtils;
import edu.johnshopkins.lovelypaws.beans.AddressInfo;
import edu.johnshopkins.lovelypaws.dao.ShelterHibernateDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static edu.johnshopkins.lovelypaws.UserInputUtils.isValidEmailAddress;
import static edu.johnshopkins.lovelypaws.UserInputUtils.isValidName;
import static edu.johnshopkins.lovelypaws.UserInputUtils.trimAndUpper;

@Component
public class ShelterBoImpl implements ShelterBo {

    @Autowired
    private ShelterHibernateDao shelterDao;

    public Shelter createShelter(String username, String password, String name, String description, AddressInfo addressInfo, String phoneNumber, String emailAddress,  List<AnimalType> animalTypes) {
        username = trimAndUpper(username);
        password = StringUtils.trimToNull(password);
        name = trimAndUpper(name);
        description = trimAndUpper(description);
        trimAndUpper(addressInfo);
        phoneNumber = trimAndUpper(phoneNumber);
        emailAddress = trimAndUpper(emailAddress);

        Shelter shelter = new Shelter();

        if(username == null) {
            throw new IllegalArgumentException("Invalid username.");
        } else if(shelterDao.findByName(username) != null) {
            throw new IllegalArgumentException("Username is already in use.");
        }

        shelter.setUsername(username);

        if(password == null) {
            throw new IllegalArgumentException("Password must be provided.");
        }
        shelter.setPasswordSha512(DigestUtils.sha512Hex(password));

        if(!isValidEmailAddress(emailAddress)) {
            throw new IllegalArgumentException("Invalid e-mail address.");
        }
        shelter.setEmailAddress(emailAddress);

        if(!isValidAddressInfo(addressInfo)) {
            throw new IllegalArgumentException("The provided address information is not valid.");
        }

        Address address = new Address();
        mergeAddress(address, addressInfo);
        shelter.setAddress(address);

        if(!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name.");
        }
        shelter.setName(name);

        if(description == null) {
            throw new IllegalArgumentException("Description must be provided.");
        }
        shelter.setDescription(description);
        ;
        if(phoneNumber == null) {
            throw new IllegalArgumentException("Phone number must be provided.");
        }
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

    private void mergeAddress(Address address, AddressInfo addressInfo) {
        address.setLine1(addressInfo.getLine1());
        address.setLine2(StringUtils.trimToNull(addressInfo.getLine2()));
        address.setCity(addressInfo.getCity());
        address.setState(addressInfo.getState());
        address.setZip(addressInfo.getZip());
    }
}

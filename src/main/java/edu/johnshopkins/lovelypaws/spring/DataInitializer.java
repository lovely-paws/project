package edu.johnshopkins.lovelypaws.spring;

import edu.johnshopkins.lovelypaws.Age;
import edu.johnshopkins.lovelypaws.Gender;
import edu.johnshopkins.lovelypaws.beans.ApplicationInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.dao.*;
import edu.johnshopkins.lovelypaws.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private AnimalTypeDao animalTypeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShelterHibernateDao shelterDao;

    @Autowired
    private AdoptionRequestBo adoptionRequestBo;

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private AdoptionRequestDao adoptionRequestDao;

    @PostConstruct
    public void init() {
        // Create canned animal types.
        if(animalTypeDao.findByName("CAT") == null) {
            log.debug("Creating CAT animal type.");
            AnimalType cat = new AnimalType();
            cat.setName("CAT");
            cat.setDescription("Felines of all shapes and sizes.");
            animalTypeDao.persist(cat);
        }

        if(animalTypeDao.findByName("DOG") == null) {
            log.debug("Creating DOG animal type.");
            AnimalType dog = new AnimalType();
            dog.setName("DOG");
            dog.setDescription("Man's (and woman's) best friend.");
            animalTypeDao.persist(dog);
        }

        // Create a canned shelter.
        if(shelterDao.findByName("MUTT'S") == null) {
            log.debug("Creating the MUTT'S shelter.");
            Shelter shelter = new Shelter();
            shelter.setUsername("mutts");
            shelter.setPasswordSha512(DigestUtils.sha512Hex("mutts"));
            shelter.setApproved(true);
            shelter.setEmailAddress("mbarten1jhu@gmail.com");
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

        if(shelterDao.findByName("PAWS OF PURRSIA") == null) {
            log.debug("Creating the PAWS OF PURRSIA shelter.");
            Shelter shelter = new Shelter();
            shelter.setUsername("pawsofpurrsia");
            shelter.setPasswordSha512(DigestUtils.sha512Hex("pawsofpurrsia"));
            shelter.setApproved(true);
            shelter.setEmailAddress("pawsofpurrsia@example.com");
            shelter.setName("PAWS OF PURRSIA");
            shelter.setDescription("The most luxurious cats around!");
            shelter.setPhoneNumber("1-800-PUR-RSIA");

            Address address = new Address();
            address.setLine1("345 Maple Lane");
            address.setCity("Mycity");
            address.setState("VA");
            address.setZip("22102");
            shelter.setAddress(address);

            shelterDao.persist(shelter);
        }

        // Create a canned administrator and end-user.
        if(!userDao.usernameExists("ADMIN")) {
            log.debug("Creating the ADMIN user.");
            Administrator administrator = new Administrator();
            administrator.setUsername("ADMIN");
            administrator.setPasswordSha512(DigestUtils.sha512Hex("admin"));
            administrator.setEmailAddress("admin@example.com");
            userDao.persist(administrator);
        }


        log.debug("Creating the USER user.");
        EndUser endUser = new EndUser();
        endUser.setUsername("USER");
        endUser.setPasswordSha512(DigestUtils.sha512Hex("user"));
        endUser.setEmailAddress("user@example.com");
        endUser.setName("A. User");
        userDao.persist(endUser);

        log.debug("Creating the USER2 user.");
        EndUser endUser2 = new EndUser();
        endUser2.setUsername("USER2");
        endUser2.setPasswordSha512(DigestUtils.sha512Hex("user2"));
        endUser2.setEmailAddress("user2@example.com");
        endUser2.setName("B. User");
        userDao.persist(endUser2);

        log.debug("Creating the BOWSER dog listing for MUTT'S.");
        Listing bowserListing = new Listing();
        bowserListing.setVisible(true);
        bowserListing.setShelter(shelterDao.findByName("MUTT'S"));
        bowserListing.setAnimalType(animalTypeDao.findByName("DOG"));
        bowserListing.setName("BOWSER");
        bowserListing.setDescription("A dog that barks a lot.");
        bowserListing.setColor("Black");
        bowserListing.setAge(Age.YOUNG);
        bowserListing.setGender(Gender.MALE);

        // Load canned picture.
        try {
            File bowserImage = new File("/tmp/bowser.jpg");
            FileUtils.copyInputStreamToFile(new ClassPathResource("../bowser.jpg").getInputStream(), bowserImage);
            bowserListing.setImageFile(bowserImage);
        } catch(IOException ioException) {
            log.error("Could not associate an image with BOWSER.", ioException);
        }
        listingDao.persist(bowserListing);

        log.debug("Creating the GARFIELD dog listing for POP.");
        Listing garfieldListing = new Listing();
        garfieldListing.setVisible(true);
        garfieldListing.setShelter(shelterDao.findByName("PAWS OF PURRSIA"));
        garfieldListing.setAnimalType(animalTypeDao.findByName("CAT"));
        garfieldListing.setName("GARFIELD");
        garfieldListing.setDescription("A cat that likes lasagna but hates Mondays.");
        garfieldListing.setColor("ORANGE");
        garfieldListing.setAge(Age.ADULT);
        garfieldListing.setGender(Gender.MALE);
        // Load canned picture.
        try {
            File garfieldImage = new File("/tmp/garfield.png");
            FileUtils.copyInputStreamToFile(new ClassPathResource("../garfield.jpg").getInputStream(), garfieldImage);
            garfieldListing.setImageFile(garfieldImage);
        } catch(IOException ioException) {
            log.error("Could not associate an image with GARFIELD.", ioException);
        }

        listingDao.persist(garfieldListing);

        log.debug("Creating canned applications for BOWSER...");
        Set<Long> bowserId = new HashSet<>(Arrays.asList(bowserListing.getId()));

        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.setWhy("I really like animals and want to provide them with a save and loving home.");
        applicationInfo.setEndUser(endUser);
        applicationInfo.setAccepted(true);
        applicationInfo.getListingIds().addAll(bowserId);

        ApplicationInfo applicationInfo2 = new ApplicationInfo();
        applicationInfo2.setWhy("I am an animal hoarder.");
        applicationInfo2.setEndUser(endUser2);
        applicationInfo2.setAccepted(true);
        applicationInfo2.getListingIds().addAll(bowserId);

        adoptionRequestBo.create(applicationInfo);
        adoptionRequestBo.create(applicationInfo2);
    }

}
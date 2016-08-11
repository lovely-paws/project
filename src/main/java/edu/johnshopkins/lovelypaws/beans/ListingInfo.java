package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.Age;
import edu.johnshopkins.lovelypaws.Gender;
import edu.johnshopkins.lovelypaws.entity.Listing;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ListingInfo {
    private long id;
    private String name;
    private String description;
    private String color;
    private long animalTypeId;
    private Gender gender;
    private Age age;
    private File imageFile;

    public ListingInfo() { }
    public ListingInfo(Listing listing) {
        if(listing != null) {
            this.id = listing.getId();
            this.name = listing.getName();
            this.description = listing.getDescription();
            this.color = listing.getColor();
            this.animalTypeId = listing.getAnimalType().getId();
            this.gender = listing.getGender();
            this.age = listing.getAge();
            this.imageFile = listing.getImageFile();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(long animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}

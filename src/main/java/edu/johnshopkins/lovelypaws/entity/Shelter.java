package edu.johnshopkins.lovelypaws.entity;

import java.util.ArrayList;
import java.util.List;

public class Shelter extends AbstractUser {
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    protected Address address;
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    protected String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    protected List<AnimalType> animalTypes = new ArrayList<AnimalType>();
    public List<AnimalType> getAnimalTypes() { return animalTypes; }
    public void setAnimalTypes(List<AnimalType> animalTypes) { this.animalTypes = animalTypes; }
}

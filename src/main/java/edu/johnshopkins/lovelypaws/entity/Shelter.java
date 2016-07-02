package edu.johnshopkins.lovelypaws.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shelter extends AbstractUser {

    @Column(name = "SHELTER_ID")
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column
    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Address address;
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Column
    protected String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    // One-way join.
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="SHELTER_ANIMAL_TYPES", joinColumns = {@JoinColumn(name="SHELTER_ID", referencedColumnName = "SHELTER_ID")})
    protected List<AnimalType> animalTypes = new ArrayList<AnimalType>();
    public List<AnimalType> getAnimalTypes() { return animalTypes; }
    public void setAnimalTypes(List<AnimalType> animalTypes) { this.animalTypes = animalTypes; }
}

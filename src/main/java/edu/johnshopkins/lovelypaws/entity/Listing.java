package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Age;
import edu.johnshopkins.lovelypaws.Gender;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Listing {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    /** The one-directional join which associates an AnimalType (e.g., DOG) to this listing. */
    @ManyToOne(fetch = FetchType.EAGER)
    protected AnimalType animalType;
    public AnimalType getAnimalType() { return animalType; }
    public void setAnimalType(AnimalType animalType) { this.animalType = animalType; }

    /** The pet's name. */
    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    /** The description for the pet. */
    @Column
    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = StringUtils.trimToNull(description); }

    /** The color of the pet. */
    @Column
    protected String color;
    public String getColor() { return color; }
    public void setColor(String color) { this.color = StringUtils.upperCase(StringUtils.trimToNull(color)); }

    /** The bi-directional join which maps a Listing entity back to the originating Shelter. */
    @ManyToOne
    protected Shelter shelter;
    public Shelter getShelter() { return shelter; }
    public void setShelter(Shelter shelter) { this.shelter = shelter; }

    @Column
    protected boolean visible;
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    @Column
    protected Age age;
    public Age getAge() { return age; }
    public void setAge(Age age) { this.age = age; }

    @Column
    protected Gender gender;
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    @Column
    protected File imageFile;
    public File getImageFile() { return imageFile; }
    public void setImageFile(File imageFile) { this.imageFile = imageFile; }

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, targetEntity = AdoptionRequest.class)
    protected List<AdoptionRequest> adoptionRequests = new ArrayList<>();
    public List<AdoptionRequest> getAdoptionRequests() { return adoptionRequests; }
    public void setAdoptionRequests(List<AdoptionRequest> adoptionRequests) { this.adoptionRequests = adoptionRequests; }

    public String toString() { return String.format("%s#<id=%d>", this.getClass().getSimpleName(), id); }
}

package edu.johnshopkins.lovelypaws.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
public class Listing {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    @Column
    protected AnimalType animalType;
    public AnimalType getAnimalType() { return animalType; }
    public void setAnimalType(AnimalType animalType) { this.animalType = animalType; }

    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    @Column
    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Column
    protected String color;
    public String getColor() { return color; }
    public void setColor(String color) { this.color = StringUtils.upperCase(StringUtils.trimToNull(color)); }

    @ManyToOne
    protected Shelter shelter;
    public Shelter getShelter() { return shelter; }
    public void setShelter(Shelter shelter) { this.shelter = shelter; }

    public String toString() { return String.format("%s#<id=%d>", this.getClass().getSimpleName(), id); }
}

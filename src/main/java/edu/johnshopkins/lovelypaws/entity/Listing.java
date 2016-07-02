package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.entity.enums.Color;

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
    public void setName(String name) { this.name = name; }

    @Column
    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Column
    @Enumerated(EnumType.STRING) // Store as BROWN as opposed to 2.
    protected Color color;
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    @ManyToOne
    protected Shelter shelter;
    public Shelter getShelter() { return shelter; }
    public void setShelter(Shelter shelter) { this.shelter = shelter; }

    public String toString() { return String.format("%s#<id=%d>", this.getClass().getSimpleName(), id); }
}

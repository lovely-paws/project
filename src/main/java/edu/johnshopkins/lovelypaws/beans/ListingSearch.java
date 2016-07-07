package edu.johnshopkins.lovelypaws.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ListingSearch implements Serializable {

    public ListingSearch() { }

    private Set<Long> animalTypeIds = new HashSet<>();
    public Set<Long> getAnimalTypeIds() { return animalTypeIds; }
    public void setAnimalTypeIds(Set<Long> animalTypeIds) { this.animalTypeIds = animalTypeIds; }

    private Set<Long> shelterIds = new HashSet<>();
    public Set<Long> getShelterIds() { return shelterIds; }
    public void setShelterIds(Set<Long> shelterIds) { this.shelterIds = shelterIds; }

    private String color;
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String toString() { return String.format("%s#<>", this.getClass().getSimpleName()); }
}

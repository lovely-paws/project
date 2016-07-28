package edu.johnshopkins.lovelypaws.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ListingSearch implements Serializable {

    public ListingSearch() { }

    private Long animalTypeId;
    public Long getAnimalTypeId() { return animalTypeId; }
    public void setAnimalTypeId(Long animalTypeId) { this.animalTypeId = animalTypeId; }

    private Long shelterId;
    public Long getShelterId() { return shelterId; }
    public void setShelterId(Long shelterId) { this.shelterId = shelterId; }

    public String toString() { return String.format("%s#<>", this.getClass().getSimpleName()); }
}

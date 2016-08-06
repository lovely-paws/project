package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.AnimalType;

public class AnimalTypeInfo {

    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public AnimalTypeInfo() { }
    public AnimalTypeInfo(AnimalType animalType) {
        if(animalType != null) {
            this.id = animalType.getId();
            this.name = animalType.getName();
            this.description = animalType.getDescription();
        }
    }

}

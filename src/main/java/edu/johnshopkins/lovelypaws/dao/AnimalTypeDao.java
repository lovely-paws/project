package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AnimalType;

public interface AnimalTypeDao extends Dao<AnimalType> {
    AnimalType findByName(String name);
}

package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.AnimalTypeInfo;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.entity.AnimalType;

public interface AnimalTypeBo {
    ServerResponse<AnimalType> create(AnimalTypeInfo animalTypeInfo);
    ServerResponse<AnimalType> update(AnimalTypeInfo animalTypeInfo);
}

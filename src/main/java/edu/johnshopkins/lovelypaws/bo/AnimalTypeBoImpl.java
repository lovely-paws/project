package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.AnimalTypeInfo;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AnimalTypeBoImpl implements AnimalTypeBo {

    @Autowired
    private AnimalTypeDao animalTypeDao;

    private boolean isValidName(String name) {
        int length = StringUtils.trimToEmpty(name).length();
        return 0 < length && length <= 128;
    }

    private boolean isUnique(String name) {
        return animalTypeDao.findByName(name) == null;
    }

    private boolean isValidDescription(String description) {
        int length = StringUtils.trimToEmpty(description).length();
        return 0 < length && length <= 255;
    }

    public ServerResponse<AnimalType> create(AnimalTypeInfo animalTypeInfo) {
        if(animalTypeInfo == null) {
            return new ServerResponse<>(false, "The request body was empty.", null);
        } else if(!isValidName(animalTypeInfo.getName())) {
            return new ServerResponse<>(false, "The provided name was not valid.", null);
        } else if(!isUnique(animalTypeInfo.getName())) {
            return new ServerResponse<>(false, "The provided name is already is use.", null);
        } else if(!isValidDescription(animalTypeInfo.getDescription())) {
            return new ServerResponse<>(false, "The provided description was not valid.", null);
        } else {
            AnimalType animalType = new AnimalType();
            animalType.setName(animalTypeInfo.getName());
            animalType.setDescription((animalTypeInfo.getDescription()));
            animalType = animalTypeDao.persist(animalType);
            return new ServerResponse<>(true, "Animal type created.", animalType);
        }
    }

    public ServerResponse<AnimalType> update(AnimalTypeInfo animalTypeInfo) {
        AnimalType animalType = null;
        if(animalTypeInfo == null) {
            return new ServerResponse<>(false, "The request body was empty.", null);
        } else if((animalType = animalTypeDao.findById(animalTypeInfo.getId())) == null) {
            return new ServerResponse<>(false, "The referenced animal type does not exist.", null);
        } else if(!isValidName(animalTypeInfo.getName())) {
            return new ServerResponse<>(false, "The provided name was not valid.", null);
        } else if(!isUnique(animalTypeInfo.getName())) {
            return new ServerResponse<>(false, "The provided name is already is use.", null);
        } else if(!isValidDescription(animalTypeInfo.getDescription())) {
            return new ServerResponse<>(false, "The provided description was not valid.", null);
        } else {
            animalType.setName(animalTypeInfo.getName());
            animalType.setDescription((animalTypeInfo.getDescription()));
            animalType = animalTypeDao.merge(animalType);
            return new ServerResponse<>(true, "Animal type updated.", animalType);
        }
    }
}

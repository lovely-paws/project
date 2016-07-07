package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AnimalTypeBoImpl implements AnimalTypeBo {

    @Autowired
    private AnimalTypeDao animalTypeDao;

    @PostConstruct
    private void init() {
        if(animalTypeDao.findByName("CAT") == null) {
            AnimalType cat = new AnimalType();
            cat.setName("CAT");
            cat.setDescription("Felines of all shapes and sizes.");
            animalTypeDao.persist(cat);
        }

        if(animalTypeDao.findByName("DOG") == null) {
            AnimalType dog = new AnimalType();
            dog.setName("DOG");
            dog.setDescription("Man's (and woman's) best friend.");
            animalTypeDao.persist(dog);
        }
    }
}

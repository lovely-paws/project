package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AnimalType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalTypeDaoImpl extends AbstractHibernateDao<AnimalType> implements AnimalTypeDao {

    public AnimalTypeDaoImpl() { setClazz(AnimalType.class);}

    public AnimalType findByName(String name) {
        name = StringUtils.upperCase(StringUtils.trimToNull(name));
        if(name == null) {
            return null;
        }

        List<AnimalType> animalTypes = sessionFactory.createQuery("from "+AnimalType.class.getSimpleName()+" where name=:name")
                .setParameter("name", name)
                .getResultList();
        return animalTypes.size() == 0 ? null : animalTypes.get(0);
    }
}

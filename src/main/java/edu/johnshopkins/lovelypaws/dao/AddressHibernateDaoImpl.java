package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressHibernateDaoImpl extends AbstractHibernateDao<Address> implements AddressHibernateDao {
    public AddressHibernateDaoImpl() {
        clazz = (Address.class);
    }
}

package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<AbstractUser> implements UserDao {
    public UserDaoImpl() { setClazz(AbstractUser.class); }

    public AbstractUser findByUsernameAndPasswordHash(String username, String passwordHash) {
        List list = sessionFactory.createQuery("from "+ AbstractUser.class.getName() +" where username=:username and passwordSha512=:passwordHash")
                .setParameter("username", username)
                .setParameter("passwordHash", passwordHash)
                .getResultList();
        if(list.size() > 0) {
            AbstractUser abstractUser = (AbstractUser)list.get(0);
            System.out.printf("[User %d] Login successful.%n", abstractUser.getId());
            return abstractUser;
        } else {
            return null;
        }
    }
}

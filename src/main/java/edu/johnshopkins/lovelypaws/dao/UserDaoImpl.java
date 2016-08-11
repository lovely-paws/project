package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<AbstractUser> implements UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl() { clazz = (AbstractUser.class); }

    public AbstractUser findByUsernameAndPasswordHash(String username, String passwordHash) {
        username = StringUtils.upperCase(StringUtils.trimToNull(username));
        passwordHash = StringUtils.lowerCase(StringUtils.trimToNull(passwordHash));
        if(username == null || passwordHash == null) {
            return null;
        }

        List list = sessionFactory.createQuery("from "+ AbstractUser.class.getName() +" where username=:username and passwordSha512=:passwordHash")
                .setParameter("username", username)
                .setParameter("passwordHash", passwordHash)
                .getResultList();
        if(list.size() > 0) {
            AbstractUser abstractUser = (AbstractUser)list.get(0);
            log.info("User '{}' has successfully logged in.", abstractUser.getUsername());
            return abstractUser;
        } else {
            return null;
        }
    }

    public boolean usernameExists(String username) {
        username = StringUtils.upperCase(StringUtils.trimToNull(username));
        return username != null && sessionFactory.createQuery("from "+AbstractUser.class.getName()+" where username=:username")
                .setParameter("username", username)
                .getResultList().size() > 0;
    }

    public boolean emailAddressExists(String emailAddress) {
        emailAddress = StringUtils.upperCase(StringUtils.trimToNull(emailAddress));
        return emailAddress != null && sessionFactory.createQuery("from "+AbstractUser.class.getName()+" where emailAddress=:emailAddress")
                .setParameter("emailAddress", emailAddress)
                .getResultList().size() > 0;
    }
}

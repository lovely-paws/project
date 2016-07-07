package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EndUserBoImpl implements EndUserBo {

    @Autowired
    private UserDao userDao;

    public EndUser create(EndUser endUser) {
        if(endUser == null) {
            return null;
        }

        String username = StringUtils.trimToNull(endUser.getUsername());
        String password = StringUtils.trimToNull(endUser.getPasswordSha512());
        String emailAddress = StringUtils.trimToNull(endUser.getEmailAddress());
        String name = StringUtils.trimToNull(endUser.getName());

        if(username == null) {
            throw new IllegalArgumentException("username");
        } else if(userDao.usernameExists(username)) {
            throw new IllegalArgumentException("username in use");
        }

        if(password == null) {
            throw new IllegalArgumentException("password");
        }

        if(emailAddress == null) {
            throw new IllegalArgumentException("emailAddress");
        } else if(userDao.emailAddressExists(emailAddress)) {
            throw new IllegalArgumentException("emailAddress in use");
        }

        if(name == null) {
            throw new IllegalArgumentException("name");
        }

        endUser.setUsername(username);
        endUser.setPasswordSha512(DigestUtils.sha512Hex(password));
        endUser.setEmailAddress(emailAddress);
        endUser.setName(name);
        return (EndUser)(userDao.persist(endUser));
    }
}

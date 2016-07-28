package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import edu.johnshopkins.lovelypaws.entity.Administrator;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserBoImpl implements UserBo {

    @Autowired
    private UserDao userDao;

    public AbstractUser login(String username, String password) {
        username = StringUtils.trimToNull(username);
        password = StringUtils.trimToNull(password);
        if(username != null && password != null) {
            return userDao.findByUsernameAndPasswordHash(username, DigestUtils.sha512Hex(password));
        } else {
            return null;
        }
    }
}

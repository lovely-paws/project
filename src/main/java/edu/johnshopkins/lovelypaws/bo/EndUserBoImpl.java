package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.CreateUserRequest;
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

    public EndUser create(CreateUserRequest createUserRequest) {
        if(createUserRequest == null) {
            return null;
        }

        String username = StringUtils.trimToNull(createUserRequest.getUsername());
        String password = StringUtils.trimToNull(createUserRequest.getPasswordSha512());
        String emailAddress = StringUtils.trimToNull(createUserRequest.getEmail());
        String name = StringUtils.trimToNull(createUserRequest.getName());

        if(username == null) {
            throw new IllegalArgumentException("A username must be provided.");
        } else if(userDao.usernameExists(username)) {
            throw new IllegalArgumentException("The requested username is in use.");
        }

        if(password == null) {
            throw new IllegalArgumentException("A non-empty password must be provided.");
        }

        if(emailAddress == null) {
            throw new IllegalArgumentException("An e-mail address must be provided.");
        } else if(userDao.emailAddressExists(emailAddress)) {
            throw new IllegalArgumentException("The provided e-mail address is already in use.");
        }

        if(name == null) {
            throw new IllegalArgumentException("A name must be provided.");
        }

        EndUser endUser = new EndUser();
        endUser.setUsername(username);
        endUser.setPasswordSha512(DigestUtils.sha512Hex(password));
        endUser.setEmailAddress(emailAddress);
        endUser.setName(name);
        return (EndUser)(userDao.persist(endUser));
    }
}

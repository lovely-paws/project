package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.*;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static edu.johnshopkins.lovelypaws.UserInputUtils.isValidEmailAddress;
import static edu.johnshopkins.lovelypaws.UserInputUtils.isValidName;
import static edu.johnshopkins.lovelypaws.UserInputUtils.trimAndUpper;

@Component
public class UserBoImpl implements UserBo {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShelterBo shelterBo;

        // =================================================================================================================
    // Interface methods
    // =================================================================================================================

    public AbstractUser login(String username, String password) {
        username = trimAndUpper(username);
        password = StringUtils.trimToNull(password);
        if(username != null && password != null) {
            return userDao.findByUsernameAndPasswordHash(username, DigestUtils.sha512Hex(password));
        } else {
            return null;
        }
    }

    @Transactional
    public ServerResponse<User> update(AccountInfo accountInfo) {
        User user = null;
        if(accountInfo == null) {
            return new ServerResponse<>(false, "The request body was empty.", null);
        } else if((user = userDao.findById(accountInfo.getId())) == null) {
            return new ServerResponse<>(false, "The referenced user does not exist.", null);
        }

        String rawPassword = StringUtils.trimToNull(accountInfo.getPassword());
        if(rawPassword != null) {
            user.setPasswordSha512(DigestUtils.sha512Hex(rawPassword));
        }

        String emailAddress = StringUtils.trimToNull(accountInfo.getEmailAddress());
        if(isValidEmailAddress(emailAddress)) {
            user.setEmailAddress(emailAddress);
        } else {
            return new ServerResponse<>(false, "Invalid e-mail address.", null);
        }

        if(accountInfo instanceof EndUserInfo) {
            return updateEndUserInfo((EndUserInfo)accountInfo, (EndUser)user);
        } else if(accountInfo instanceof ShelterInfo) {
            return updateShelterInfo((ShelterInfo)accountInfo, (Shelter)user);
        } else if(accountInfo instanceof AdministratorInfo) {
            return updateAdministratorInfo((AdministratorInfo)accountInfo, (Administrator)user);
        } else {
            return new ServerResponse<>(false, "Unknown account type.", null);
        }
    }

    // =================================================================================================================
    // Type-specific update methods
    // =================================================================================================================

    private ServerResponse<User> updateShelterInfo(ShelterInfo shelterInfo, Shelter shelter) {
        if(shelterInfo == null) {
            return new ServerResponse<>(false, "Empty response body.", null);
        }

        String name = trimAndUpper(shelterInfo.getName());
        String description = trimAndUpper(shelterInfo.getDescription());
        String phoneNumber = trimAndUpper(shelterInfo.getPhoneNumber());
        trimAndUpper(shelterInfo.getAddressInfo());

        if(!isValidName(name)) {
            return new ServerResponse<>(false, "Invalid name.", null);
        } else if(!shelterBo.isValidDescription(description)) {
            return new ServerResponse<>(false, "Invalid description.", null);
        } else if(!shelterBo.isValidPhoneNumber(phoneNumber)) {
            return new ServerResponse<>(false, "Invalid phone number.", null);
        } else if(!shelterBo.isValidAddressInfo(shelterInfo.getAddressInfo())) {
            return new ServerResponse<>(false, "Invalid address.", null);
        }

        shelter.setName(name);
        shelter.setDescription(description);
        shelter.setPhoneNumber(phoneNumber);
        mergeAddress(shelter.getAddress(), shelterInfo.getAddressInfo());

        return new ServerResponse<>(true, "Shelter updated.", userDao.merge(shelter));
    }

    private void mergeAddress(Address address, AddressInfo addressInfo) {
        address.setLine1(addressInfo.getLine1());
        address.setLine2(StringUtils.trimToNull(addressInfo.getLine2()));
        address.setCity(addressInfo.getCity());
        address.setState(addressInfo.getState());
        address.setZip(addressInfo.getZip());
    }

    private ServerResponse<User> updateEndUserInfo(EndUserInfo endUserInfo, EndUser endUser) {
        String name = trimAndUpper(endUserInfo.getName());
        if(isValidName(name)) {
            endUser.setName(name);
            return new ServerResponse<>(true, "", userDao.merge(endUser));
        } else {
            return new ServerResponse<>(false, "Invalid name.", null);
        }
    }

    private ServerResponse<User> updateAdministratorInfo(AdministratorInfo administratorInfo, Administrator administrator) {
        return new ServerResponse<>(true, "", userDao.merge(administrator));
    }
}

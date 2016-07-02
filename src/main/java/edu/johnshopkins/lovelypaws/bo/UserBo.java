package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.entity.AbstractUser;

public interface UserBo {
    AbstractUser login(String username, String password);
}

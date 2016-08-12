package edu.johnshopkins.lovelypaws.bo;

import edu.johnshopkins.lovelypaws.beans.AccountInfo;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import edu.johnshopkins.lovelypaws.entity.User;

public interface UserBo {
    AbstractUser login(String username, String password);
    ServerResponse<User> update(AccountInfo accoutInfo);
}

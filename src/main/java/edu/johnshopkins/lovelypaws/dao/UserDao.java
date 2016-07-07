package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AbstractUser;

public interface UserDao extends Dao<AbstractUser> {
    boolean usernameExists(String username);
    boolean emailAddressExists(String emailAddress);
    AbstractUser findByUsernameAndPasswordHash(String username, String passwordHash);
}

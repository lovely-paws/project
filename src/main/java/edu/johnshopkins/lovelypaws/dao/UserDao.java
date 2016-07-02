package edu.johnshopkins.lovelypaws.dao;

import edu.johnshopkins.lovelypaws.entity.AbstractUser;

public interface UserDao extends Dao<AbstractUser> {
    AbstractUser findByUsernameAndPasswordHash(String username, String passwordHash);
}

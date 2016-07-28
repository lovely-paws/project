package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Role;
import org.springframework.context.annotation.Scope;

public interface User {

    long getId();

    String getUsername();
    void setUsername(String username);

    String getPasswordSha512();
    void setPasswordSha512(String passwordSha512);

    String getEmailAddress();
    void setEmailAddress(String emailAddress);

    Role getRole();

}

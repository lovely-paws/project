package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Role;

import javax.persistence.Entity;

@Entity
public class Administrator extends AbstractUser {
    public Role getRole() { return Role.ADMINISTRATOR; }
}

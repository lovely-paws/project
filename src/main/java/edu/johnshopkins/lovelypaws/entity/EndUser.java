package edu.johnshopkins.lovelypaws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EndUser extends AbstractUser {

    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}

package edu.johnshopkins.lovelypaws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public abstract class AbstractUser implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    @Column
    protected String username;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Column
    protected String passwordSha512;
    public String getPasswordSha512() { return passwordSha512; }
    public void setPasswordSha512(String passwordSha512) { this.passwordSha512 = passwordSha512; }

    public String toString() {
        return String.format("%s#<id=%d>", this.getClass().getSimpleName(), id);
    }
}

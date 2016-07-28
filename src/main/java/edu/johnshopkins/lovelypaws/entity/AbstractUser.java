package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Role;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public abstract class AbstractUser implements User, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long id;
    public long getId() { return id; }

    @Column(unique = true)
    protected String username;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = StringUtils.upperCase(StringUtils.trimToNull(username)); }

    @Column
    private String passwordSha512;
    public String getPasswordSha512() { return passwordSha512; }
    public void setPasswordSha512(String passwordSha512) {
        passwordSha512 = StringUtils.trimToNull(passwordSha512);
        if(passwordSha512 == null) {
            throw new IllegalArgumentException("Cannot set password to a null or empty value.");
        } else {
            this.passwordSha512 = StringUtils.lowerCase(StringUtils.trimToNull(passwordSha512));
        }
    }

    @Column
    protected String emailAddress;
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = StringUtils.upperCase(StringUtils.trimToNull(emailAddress)); }

    public String toString() {
        return String.format("%s#<id=%d>", this.getClass().getSimpleName(), id);
    }

}

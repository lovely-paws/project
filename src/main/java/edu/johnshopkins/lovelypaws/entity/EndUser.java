package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Role;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EndUser extends AbstractUser {

    /** The end user's display name. */
    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    public Role getRole() { return Role.END_USER; }
}

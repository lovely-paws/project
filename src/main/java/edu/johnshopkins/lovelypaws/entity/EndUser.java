package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Role;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EndUser extends AbstractUser {

    /** The end user's display name. */
    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    /** The one-directional join to the listings owned by this Shelter. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "endUser", cascade = CascadeType.ALL)
    protected List<AdoptionRequest> adoptionRequests = new ArrayList<>();
    public List<AdoptionRequest> getAdoptionRequests() { return adoptionRequests; }
    public void setAdoptionRequests(List<AdoptionRequest> adoptionRequests) { this.adoptionRequests = adoptionRequests; }

    public Role getRole() { return Role.END_USER; }
}

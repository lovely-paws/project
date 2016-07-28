package edu.johnshopkins.lovelypaws.entity;

import edu.johnshopkins.lovelypaws.Role;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shelter extends AbstractUser {

    /** The human-readable name for this Shelter. */
    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    /** The description for this Shelter. */
    @Column
    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    /** A boolean flag indicating if this Shelter is approved to make posts. */
    @Column
    protected boolean approved = false;
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    /** A one-directional join to this Shelter's address. */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Address address;
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    /** The Shelter's phone number. */
    @Column
    protected String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = StringUtils.upperCase(StringUtils.trimToNull(phoneNumber)); }

    // One-way join.
    /** The one-directional join to the listings owned by this Shelter. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shelter")
    protected List<Listing> listings = new ArrayList<Listing>();
    public List<Listing> getListings() { return listings; }
    public void setListings(List<Listing> listings) { this.listings = listings; }

    public Role getRole() { return Role.SHELTER; }
}

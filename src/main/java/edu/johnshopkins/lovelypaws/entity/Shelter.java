package edu.johnshopkins.lovelypaws.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shelter extends AbstractUser {

    @Column
    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = StringUtils.upperCase(StringUtils.trimToNull(name)); }

    @Column
    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Address address;
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Column
    protected String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = StringUtils.upperCase(StringUtils.trimToNull(phoneNumber)); }

    // One-way join.
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shelter")
    protected List<Listing> listings = new ArrayList<Listing>();
    public List<Listing> getListings() { return listings; }
    public void setListings(List<Listing> listings) { this.listings = listings; }
}

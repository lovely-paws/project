package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.entity.User;

public class ShelterInfo extends AccountInfo {

    protected String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    protected String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    protected boolean approved;
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    protected AddressInfo addressInfo;
    public AddressInfo getAddressInfo() { return addressInfo; }
    public void setAddressInfo(AddressInfo addressInfo) { this.addressInfo = addressInfo; }

    protected String phoneNumber;
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public ShelterInfo() { }
    public ShelterInfo(User user) {
        super(user);
        if(user instanceof Shelter) {
            Shelter casted = (Shelter)user;
            this.name = casted.getName();
            this.description = casted.getDescription();
            this.approved = casted.isApproved();
            this.addressInfo = new AddressInfo(casted.getAddress());
            this.phoneNumber = casted.getPhoneNumber();
        }
    }
}

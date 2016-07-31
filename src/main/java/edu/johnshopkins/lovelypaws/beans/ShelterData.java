package edu.johnshopkins.lovelypaws.beans;

public class ShelterData {
    private String username;
    private String passwordSha512;
    private String name;
    private String emailAddress;
    private String description;
    private String phoneNumber;
    private AddressData addressData;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordSha512() {
        return passwordSha512;
    }

    public void setPasswordSha512(String passwordSha512) {
        this.passwordSha512 = passwordSha512;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressData getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressData addressData) {
        this.addressData = addressData;
    }
}

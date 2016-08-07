package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.Address;

public class AddressInfo {

    protected long id;
    public long getId() { return id; }

    protected String line1;
    public String getLine1() { return line1; }
    public void setLine1(String line1) { this.line1 = line1; }

    protected String line2;
    public String getLine2() { return line2; }
    public void setLine2(String line2) { this.line2 = line2; }

    protected String city;
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    protected String state;
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    protected String zip;
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public AddressInfo() { }
    public AddressInfo(Address address) {
        if(address != null) {
            this.id = address.getId();
            this.line1 = address.getLine1();
            this.line2 = address.getLine2();
            this.city = address.getCity();
            this.state = address.getState();
            this.zip = address.getZip();
        }
    }
}

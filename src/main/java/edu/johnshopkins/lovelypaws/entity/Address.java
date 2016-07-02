package edu.johnshopkins.lovelypaws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }

    @Column
    protected String line1;
    public String getLine1() { return line1; }
    public void setLine1(String line1) { this.line1 = line1; }

    @Column
    protected String line2;
    public String getLine2() { return line2; }
    public void setLine2(String line2) { this.line2 = line2; }

    @Column
    protected String city;
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    @Column
    protected String state;
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Column
    protected String zip;
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String toString() { return String.format("%s#<id=%d>", getClass().getSimpleName(), id); }
}

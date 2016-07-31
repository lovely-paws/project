package edu.johnshopkins.lovelypaws.entity;

import org.apache.commons.lang3.StringUtils;

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
    public void setLine1(String line1) { this.line1 = StringUtils.upperCase(StringUtils.trimToNull(line1)); }

    @Column
    protected String line2;
    public String getLine2() { return line2; }
    public void setLine2(String line2) { this.line2 = StringUtils.upperCase(StringUtils.trimToNull(line2)); }

    @Column
    protected String city;
    public String getCity() { return city; }
    public void setCity(String city) { this.city = StringUtils.upperCase(StringUtils.trimToNull(city)); }

    @Column
    protected String state;
    public String getState() { return state; }
    public void setState(String state) { this.state = StringUtils.upperCase(StringUtils.trimToNull(state)); }

    @Column
    protected String zip;
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = StringUtils.upperCase(StringUtils.trimToNull(zip)); }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(line1).append("\n");
        if(line2 != null) {
            stringBuilder.append(line2).append("\n");
        }
        stringBuilder.append(city).append(", ").append(state).append(" ").append(zip);
        return stringBuilder.toString();
    }
}

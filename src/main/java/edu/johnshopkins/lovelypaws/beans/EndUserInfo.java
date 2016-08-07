package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.EndUser;
import edu.johnshopkins.lovelypaws.entity.User;

public class EndUserInfo extends AccountInfo {

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public EndUserInfo() { }
    public EndUserInfo(User user) {
        super(user);
        if(user instanceof EndUser) {
            this.name = ((EndUser) user).getName();
        }
    }
}

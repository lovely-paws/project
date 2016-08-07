package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.Administrator;
import edu.johnshopkins.lovelypaws.entity.User;

public class AdministratorInfo extends AccountInfo {

    public AdministratorInfo() { }
    public AdministratorInfo(User user) {
        super(user);
        if(user instanceof Administrator) {
            // set admin-specific fields
        }
    }
}

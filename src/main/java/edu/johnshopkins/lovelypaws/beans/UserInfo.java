package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserInfo {

    private User user;
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}

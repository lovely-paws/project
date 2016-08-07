package edu.johnshopkins.lovelypaws.beans;

import edu.johnshopkins.lovelypaws.entity.User;

public class AccountInfo {
    protected long id;
    protected String username;
    protected String password;
    protected String emailAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public AccountInfo() { }

    public AccountInfo(User user) {
        if(user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.emailAddress = user.getEmailAddress();
        }
    }
}

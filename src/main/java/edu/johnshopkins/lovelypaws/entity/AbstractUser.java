package edu.johnshopkins.lovelypaws.entity;

public abstract class AbstractUser {
    protected String username;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    protected String passwordSha512;
    public String getPasswordSha512() { return passwordSha512; }
    public void setPasswordSha512(String passwordSha512) { this.passwordSha512 = passwordSha512; }
}

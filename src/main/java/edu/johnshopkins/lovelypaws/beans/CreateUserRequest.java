package edu.johnshopkins.lovelypaws.beans;

public class CreateUserRequest {
    private String username;
    private String name;
    private String passwordSha512;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordSha512() {
        return passwordSha512;
    }

    public void setPasswordSha512(String passwordSha512) {
        this.passwordSha512 = passwordSha512;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

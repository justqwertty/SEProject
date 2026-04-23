package me.seproject.mavenproject1.entity;

public class User extends Person {
    private String userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean login;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean login() {
        return false;
    }

    public void logout() {
    }

    public boolean authenticate(String username, String password) {
        return false;
    }
}

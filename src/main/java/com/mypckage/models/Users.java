package com.mypackage.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is the user's object
 */
public class Users {
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String secret;
    private String roleID;
    private ArrayList<Reimb> reimbs;

    public Users() {
    }

    public Users(int userID, String userName, String firstName, String lastName, String email, String secret,
                 String roleID, ArrayList<Reimb> reimbs) {
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.secret = secret;
        this.roleID = roleID;
        this.reimbs = reimbs;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public ArrayList<Reimb> getReimbs() {
        return reimbs;
    }

    public void setReimbs(ArrayList<Reimb> reimbs) {
        this.reimbs = reimbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userID == users.userID && Objects.equals(userName, users.userName) && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(email, users.email) && Objects.equals(secret, users.secret) && Objects.equals(roleID, users.roleID) && Objects.equals(reimbs, users.reimbs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, firstName, lastName, email, secret, roleID, reimbs);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", secret='" + secret + '\'' +
                ", roleID=" + roleID +
                ", reimbs=" + reimbs +
                '}';
    }
}

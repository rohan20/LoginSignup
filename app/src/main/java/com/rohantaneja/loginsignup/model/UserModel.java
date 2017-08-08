package com.rohantaneja.loginsignup.model;

/**
 * Created by rohantaneja on 08/08/17.
 */

public class UserModel {

    int userId;
    String userEmail;
    String userPassword;

    public UserModel(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

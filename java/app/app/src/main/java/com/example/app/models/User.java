package com.example.app.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String userID;
    private String email;
    private String name;
    private String surname;

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String userID, String email, String name, String surname) {
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User:{" +
                "userID='" + userID + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static User parseUser(JSONObject object){

        User user = new User();
        try {
            user.setUserID(object.get("user_id").toString());
            user.setEmail(object.get("email").toString());
            user.setName(object.get("name").toString());
            user.setSurname(object.get("surname").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

}

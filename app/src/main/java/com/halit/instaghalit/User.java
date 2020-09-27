package com.halit.instaghalit;

/**
 * Created by android on 9/28/20.
 */

public class User {

    String email,username,image;
    int id;

    public User(int id,String email, String username) {
        this.email = email;
        this.username = username;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

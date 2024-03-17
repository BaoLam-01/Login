package com.example.login.object;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String email;
    private String password;
    private String address;

    public User() {
    }

    public User(int id, String email, String address, String password) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {

        return super.toString() + getId() + getEmail() + getPassword() + getAddress();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

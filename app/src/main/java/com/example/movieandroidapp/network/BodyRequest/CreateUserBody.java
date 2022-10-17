package com.example.movieandroidapp.network.BodyRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUserBody{
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;


    public CreateUserBody(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public CreateUserBody() {
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
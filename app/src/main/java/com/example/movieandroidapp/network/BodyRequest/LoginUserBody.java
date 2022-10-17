package com.example.movieandroidapp.network.BodyRequest;

public class LoginUserBody {
    private String userName;
    private String password;

    public LoginUserBody() {
    }

    public LoginUserBody(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

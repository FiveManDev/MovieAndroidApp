package com.example.movieandroidapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayLoadToken {

    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("AuthorizationID")
    @Expose
    private String authorizationID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("nbf")
    @Expose
    private Integer nbf;
    @SerializedName("exp")
    @Expose
    private Integer exp;
    @SerializedName("iat")
    @Expose
    private Integer iat;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAuthorizationID() {
        return authorizationID;
    }

    public void setAuthorizationID(String authorizationID) {
        this.authorizationID = authorizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getNbf() {
        return nbf;
    }

    public void setNbf(Integer nbf) {
        this.nbf = nbf;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getIat() {
        return iat;
    }

    public void setIat(Integer iat) {
        this.iat = iat;
    }

}

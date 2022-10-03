package com.example.movieandroidapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authorization {
    @SerializedName("authorizationID")
    @Expose
    private String authorizationID;
    @SerializedName("authorizationName")
    @Expose
    private String authorizationName;
    @SerializedName("authorizationLevel")
    @Expose
    private Integer authorizationLevel;

    public String getAuthorizationID() {
        return authorizationID;
    }

    public void setAuthorizationID(String authorizationID) {
        this.authorizationID = authorizationID;
    }

    public String getAuthorizationName() {
        return authorizationName;
    }

    public void setAuthorizationName(String authorizationName) {
        this.authorizationName = authorizationName;
    }

    public Integer getAuthorizationLevel() {
        return authorizationLevel;
    }

    public void setAuthorizationLevel(Integer authorizationLevel) {
        this.authorizationLevel = authorizationLevel;
    }

}

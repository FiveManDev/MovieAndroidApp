package com.example.movieandroidapp.network.BodyRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeStatusUser {
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("isBanned")
    @Expose
    private Boolean isBanned;

    public ChangeStatusUser(String userID, Boolean isBanned) {
        this.userID = userID;
        this.isBanned = isBanned;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

}

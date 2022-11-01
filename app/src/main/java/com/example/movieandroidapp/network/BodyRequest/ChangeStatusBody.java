package com.example.movieandroidapp.network.BodyRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeStatusBody {

    @SerializedName("movieID")
    @Expose
    private String movieID;
    @SerializedName("isVisible")
    @Expose
    private Boolean isVisible;

    public ChangeStatusBody(String movieID, Boolean isVisible) {
        this.movieID = movieID;
        this.isVisible = isVisible;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }



}

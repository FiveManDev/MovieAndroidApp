package com.example.movieandroidapp.network.BodyRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewBody {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("reviewContent")
    @Expose
    private String reviewContent;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("reviewTime")
    @Expose
    private String reviewTime;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("movieID")
    @Expose
    private String movieID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

}

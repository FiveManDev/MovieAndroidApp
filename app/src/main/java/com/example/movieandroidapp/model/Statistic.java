package com.example.movieandroidapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistic {

    @SerializedName("userCreatedCount")
    @Expose
    private Integer userCreatedCount;
    @SerializedName("movieCreatedCount")
    @Expose
    private Integer movieCreatedCount;
    @SerializedName("reviewCreatedCount")
    @Expose
    private Integer reviewCreatedCount;
    @SerializedName("totalMoneyGained")
    @Expose
    private Integer totalMoneyGained;

    public Integer getUserCreatedCount() {
        return userCreatedCount;
    }

    public void setUserCreatedCount(Integer userCreatedCount) {
        this.userCreatedCount = userCreatedCount;
    }

    public Integer getMovieCreatedCount() {
        return movieCreatedCount;
    }

    public void setMovieCreatedCount(Integer movieCreatedCount) {
        this.movieCreatedCount = movieCreatedCount;
    }

    public Integer getReviewCreatedCount() {
        return reviewCreatedCount;
    }

    public void setReviewCreatedCount(Integer reviewCreatedCount) {
        this.reviewCreatedCount = reviewCreatedCount;
    }

    public Integer getTotalMoneyGained() {
        return totalMoneyGained;
    }

    public void setTotalMoneyGained(Integer totalMoneyGained) {
        this.totalMoneyGained = totalMoneyGained;
    }
}

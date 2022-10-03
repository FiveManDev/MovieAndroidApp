package com.example.movieandroidapp.model.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("movieID")
    @Expose
    private String movieID;
    @SerializedName("movieName")
    @Expose
    private String movieName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("actor")
    @Expose
    private String actor;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("releaseTime")
    @Expose
    private String releaseTime;
    @SerializedName("publicationTime")
    @Expose
    private String publicationTime;
    @SerializedName("coverImage")
    @Expose
    private String coverImage;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("movieURL")
    @Expose
    private String movieURL;
    @SerializedName("runningTime")
    @Expose
    private Float runningTime;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("rating")
    @Expose
    private Float rating;
    @SerializedName("firstNameAuthor")
    @Expose
    private String firstNameAuthor;
    @SerializedName("lastNameAuthor")
    @Expose
    private String lastNameAuthor;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("movieTypeName")
    @Expose
    private String movieTypeName;

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMovieURL() {
        return movieURL;
    }

    public void setMovieURL(String movieURL) {
        this.movieURL = movieURL;
    }

    public Float getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Float runningTime) {
        this.runningTime = runningTime;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }




    public String getFirstNameAuthor() {
        return firstNameAuthor;
    }

    public void setFirstNameAuthor(String firstNameAuthor) {
        this.firstNameAuthor = firstNameAuthor;
    }

    public String getLastNameAuthor() {
        return lastNameAuthor;
    }

    public void setLastNameAuthor(String lastNameAuthor) {
        this.lastNameAuthor = lastNameAuthor;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }
}

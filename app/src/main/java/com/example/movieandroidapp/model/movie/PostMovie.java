package com.example.movieandroidapp.model.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

public class PostMovie {

    @SerializedName("movieID")
    @Expose
    private String movieID;

    @SerializedName("movieName")
    @Expose
    private String movieName;


    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("actor")
    @Expose
    private String actor;

    @SerializedName("thumbnail")
    @Expose
    private File thumbnail;

    @SerializedName("country")
    @Expose
    private String country;

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
    private File coverImage;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("movie")
    @Expose
    private File movie;

    @SerializedName("runningTime")
    @Expose
    private Float runningTime;

    @SerializedName("quality")
    @Expose
    private String quality;

    @SerializedName("className")
    @Expose
    private String className;

    @SerializedName("movieTypeName")
    @Expose
    private String movieTypeName;

    @SerializedName("genreName")
    @Expose
    private List<String> genreNames = null;

    @SerializedName("userID")
    @Expose
    private String userID;

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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public File getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public File getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(File coverImage) {
        this.coverImage = coverImage;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public File getMovie() {
        return movie;
    }

    public void setMovie(File movie) {
        this.movie = movie;
    }

    public Float getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Float runningTime) {
        this.runningTime = runningTime;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
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

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

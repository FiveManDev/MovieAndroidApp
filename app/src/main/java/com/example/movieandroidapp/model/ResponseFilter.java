package com.example.movieandroidapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseFilter<T> {
    @SerializedName(value="value", alternate={"movies", "reviews","users"})
    @Expose
    private T value = null;
    @SerializedName("pager")
    @Expose
    private Pagination pagination;

    public ResponseFilter(T value, Pagination pagination) {
        this.value = value;
        this.pagination = pagination;
    }

    public ResponseFilter() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}

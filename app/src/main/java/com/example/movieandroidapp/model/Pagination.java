package com.example.movieandroidapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pagination {
    @SerializedName("pageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("totalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("hasPrevious")
    @Expose
    private Boolean hasPrevious;
    @SerializedName("hasNext")
    @Expose
    private Boolean hasNext;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }
}

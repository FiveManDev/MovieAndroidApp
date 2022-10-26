package com.example.movieandroidapp.network.BodyRequest;

public class Filter {

    private int pageIndex, pageSize;
    private String query,  sortBy,  sortType;

    public Filter(int pageIndex, int pageSize, String query, String sortBy, String sortType) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.query = query;
        this.sortBy = sortBy;
        this.sortType = sortType;
    }

    public Filter() {
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}

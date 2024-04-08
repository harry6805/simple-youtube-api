package com.inrech.jobs.youtube.model.searchlist;

import java.util.List;

public class SearchedPage {
    protected String prevPageToken;
    protected String nextPageToken;
    protected int totalResults;
    protected int resultsPerPage;
    private String regionCode;
    private List<SearchedItem> items;

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public List<SearchedItem> getItems() {
        return items;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public void setItems(List<SearchedItem> items) {
        this.items = items;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}

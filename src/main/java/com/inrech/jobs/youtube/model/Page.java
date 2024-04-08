package com.inrech.jobs.youtube.model;

public class Page {
    protected String prevPageToken;
    protected String nextPageToken;
    protected int totalResults;
    protected int resultsPerPage;

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
}

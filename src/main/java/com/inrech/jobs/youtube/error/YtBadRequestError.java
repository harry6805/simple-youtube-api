package com.inrech.jobs.youtube.error;

public class YtBadRequestError {
    private String error;
    private int status;

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

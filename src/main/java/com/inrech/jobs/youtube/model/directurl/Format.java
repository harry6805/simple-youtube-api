package com.inrech.jobs.youtube.model.directurl;

public class Format {
    private int itag;
    private String url;
    private String mimeType;
    private int bitrate;
    private int averageBitrate;
    private int approxDurationMs;

    public int getItag() {
        return itag;
    }

    public String getUrl() {
        return url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getBitrate() {
        return bitrate;
    }

    public int getAverageBitrate() {
        return averageBitrate;
    }

    public int getApproxDurationMs() {
        return approxDurationMs;
    }

    public void setItag(int itag) {
        this.itag = itag;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public void setAverageBitrate(int averageBitrate) {
        this.averageBitrate = averageBitrate;
    }

    public void setApproxDurationMs(int approxDurationMs) {
        this.approxDurationMs = approxDurationMs;
    }
}

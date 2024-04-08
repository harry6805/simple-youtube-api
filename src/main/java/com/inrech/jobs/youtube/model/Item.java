package com.inrech.jobs.youtube.model;

import java.util.Map;

public class Item {
    private String publishedAt;
    private Map<String, String> thumbnails;
    private String channelTitle;
    private String channelId;

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Map<String, String> getThumbnails() {
        return thumbnails;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setThumbnails(Map<String, String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}

package com.inrech.jobs.youtube.model.searchlist;

import java.util.Map;

public class SearchedItem {
    private String publishedAt;
    private Map<String, String> thumbnails;
    private String channelTitle;
    private String channelId;
    private String videoId;
    private String playlistId;
    private String title;
    private String description;

    public String getPublishedAt() {
        return publishedAt;
    }

    public Map<String, String> getThumbnails() {
        return thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

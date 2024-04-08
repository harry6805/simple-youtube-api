package com.inrech.jobs.youtube.model.directurl;

import java.util.List;
import java.util.Map;

public class VideoDetails {
    private String videoId;
    private String videoTitle;
    private long viewCount;
    private int lengthSeconds;
    private String channelId;
    private String shortDescription;
    private List<Map<String, String>> thumbnails;
    private String author;

    public String getVideoId() {
        return videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public long getViewCount() {
        return viewCount;
    }

    public int getLengthSeconds() {
        return lengthSeconds;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<Map<String, String>> getThumbnails() {
        return thumbnails;
    }

    public String getAuthor() {
        return author;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public void setLengthSeconds(int lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setThumbnails(List<Map<String, String>> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

package com.inrech.jobs.youtube.model.videolist;

import com.inrech.jobs.youtube.model.Item;

public class VideoSnippet extends Item {
    private String videoId;
    private String videoTitle;
    private long viewCount;
    private boolean favored;
    private int resumeAt;

    public String getVideoId() {
        return videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public long getViewCount() {
        return viewCount;
    }

    public boolean isFavored() {
        return favored;
    }

    public int getResumeAt() {
        return resumeAt;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setFavored(boolean favored) {
        this.favored = favored;
    }

    public void setResumeAt(int resumeAt) {
        this.resumeAt = resumeAt;
    }
}

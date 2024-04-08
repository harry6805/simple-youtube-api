package com.inrech.jobs.youtube.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String publishedAt;
    @Column(length=1000, nullable=true)
    private String thumbnails;
    private String channelTitle;
    private String channelId;
    private String videoId;
    private String videoTitle;
    private long viewCount;
    private boolean favored;
    private int resumeAt;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getThumbnails() {
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

    public User getUser() {
        return user;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setThumbnails(String thumbnails) {
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

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public void setFavored(boolean favored) {
        this.favored = favored;
    }

    public void setResumeAt(int resumeAt) {
        this.resumeAt = resumeAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

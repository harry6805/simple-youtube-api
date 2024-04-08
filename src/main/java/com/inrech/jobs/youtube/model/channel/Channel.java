package com.inrech.jobs.youtube.model.channel;


import com.inrech.jobs.youtube.model.Item;

public class Channel extends Item {
    private String country;
    private int viewCount;
    private int subscriberCount;
    private int videoCount;
    private boolean hiddenSubscriberCount;

    public String getCountry() {
        return country;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public boolean isHiddenSubscriberCount() {
        return hiddenSubscriberCount;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public void setHiddenSubscriberCount(boolean hiddenSubscriberCount) {
        this.hiddenSubscriberCount = hiddenSubscriberCount;
    }
}

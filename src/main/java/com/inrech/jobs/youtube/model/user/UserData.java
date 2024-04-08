package com.inrech.jobs.youtube.model.user;

import com.inrech.jobs.youtube.model.channel.Channel;
import com.inrech.jobs.youtube.model.videolist.VideoSnippet;

import java.util.List;

public class UserData {
    private String userName;
    private String email;
    private List<VideoSnippet> userVideos;
    private List<Channel> subscribes;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<VideoSnippet> getUserVideos() {
        return userVideos;
    }

    public List<Channel> getSubscribes() {
        return subscribes;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserVideos(List<VideoSnippet> userVideos) {
        this.userVideos = userVideos;
    }

    public void setSubscribes(List<Channel> subscribes) {
        this.subscribes = subscribes;
    }
}

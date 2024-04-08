package com.inrech.jobs.youtube.model.directurl;

import java.util.List;

public class VideoUrl {
    private List<Format> audioOnly;
    private List<Format> videoOnly;
    private List<Format> audioVideo;
    private VideoDetails videoDetails;

    public VideoDetails getVideoDetails() {
        return videoDetails;
    }

    public List<Format> getAudioOnly() {
        return audioOnly;
    }

    public List<Format> getVideoOnly() {
        return videoOnly;
    }

    public List<Format> getAudioVideo() {
        return audioVideo;
    }

    public void setAudioOnly(List<Format> audioOnly) {
        this.audioOnly = audioOnly;
    }

    public void setVideoOnly(List<Format> videoOnly) {
        this.videoOnly = videoOnly;
    }

    public void setAudioVideo(List<Format> audioVideo) {
        this.audioVideo = audioVideo;
    }

    public void setVideoDetails(VideoDetails videoDetails) {
        this.videoDetails = videoDetails;
    }
}

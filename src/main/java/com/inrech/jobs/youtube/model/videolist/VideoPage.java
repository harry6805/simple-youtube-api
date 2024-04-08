package com.inrech.jobs.youtube.model.videolist;

import com.inrech.jobs.youtube.model.Page;

import java.util.List;

public class VideoPage extends Page {
    private List<VideoSnippet> videoList;
    private String regionCode;
    public String getRegionCode() {
        return regionCode;
    }
    public List<VideoSnippet> getVideoList() {
        return videoList;
    }
    public void setVideoList(List<VideoSnippet> videoList) {
        this.videoList = videoList;
    }
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}

package com.inrech.jobs.youtube.model.playlist;

import com.inrech.jobs.youtube.model.Item;

public class Playlist extends Item {
    private String playlistId;
    private String playlistTitle;

    public String getPlaylistId() {
        return playlistId;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }
}

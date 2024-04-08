package com.inrech.jobs.youtube.model.playlist;

import com.inrech.jobs.youtube.model.Page;

import java.util.List;

public class PlaylistPage extends Page {
    private List<Playlist> playlists;

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}

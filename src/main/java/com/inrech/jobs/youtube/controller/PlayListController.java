package com.inrech.jobs.youtube.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrech.jobs.youtube.model.playlist.Playlist;
import com.inrech.jobs.youtube.model.playlist.PlaylistPage;
import com.inrech.jobs.youtube.requestapi.GetList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlayListController {
    private ObjectMapper objectMapper;
    private GetList api;

    PlayListController(){
        this.api = new GetList();
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/yt/playlist")
    public PlaylistPage getPlaylist(@RequestParam(value = "pageToken", required = false) String pageToken,
                                    @RequestParam(value = "maxResults", required = false) String maxResults,
                                    @RequestParam(value = "channelId") String channelId){
        if(null == pageToken){
            pageToken = "";
        }

        String responseBody = this.api.getPlaylistOfChannel(channelId, pageToken, maxResults).block();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            PlaylistPage pp = new PlaylistPage();
            List<Playlist> pls = new ArrayList<>();

            root.withArray("items").forEach(item -> {
                Playlist pl = new Playlist();

                Map<String, String> thumbnails = new HashMap<>();
                item.get("snippet").get("thumbnails").fieldNames().forEachRemaining(key ->{
                    thumbnails.put(key, item.get("snippet").get("thumbnails").get(key).get("url").asText());
                });

                pl.setPlaylistId(item.get("id").asText());
                pl.setPlaylistTitle(item.get("snippet").get("title").asText());
                pl.setPublishedAt(item.get("snippet").get("publishedAt").asText());
                pl.setThumbnails(thumbnails);
                pl.setChannelId(item.get("snippet").get("channelTitle").asText());
                pl.setChannelTitle(channelId);

                pls.add(pl);
            });
            pp.setPlaylists(pls);

            String prevPageToken = "";
            if(root.has("prevPageToken")){
                prevPageToken = root.get("prevPageToken").asText();
            }

            pp.setPrevPageToken(prevPageToken);
            pp.setNextPageToken(root.get("nextPageToken").asText());
            pp.setTotalResults(root.get("pageInfo").get("totalResults").asInt());
            pp.setResultsPerPage(root.get("pageInfo").get("resultsPerPage").asInt());

            return pp;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return new PlaylistPage();
        }
    }
}

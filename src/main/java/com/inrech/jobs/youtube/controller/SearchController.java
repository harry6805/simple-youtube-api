package com.inrech.jobs.youtube.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrech.jobs.youtube.model.searchlist.SearchedItem;
import com.inrech.jobs.youtube.model.searchlist.SearchedPage;
import com.inrech.jobs.youtube.requestapi.GetList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {
    private final ObjectMapper objectMapper;
    private final GetList api;

    SearchController(){
        this.objectMapper = new ObjectMapper();
        this.api = new GetList();
    }

    @GetMapping("/yt/search")
    public SearchedPage searchYt(@RequestParam(value = "channelId", required = false) String channelId,
                                             @RequestParam(value = "q", required = false) String queryStr,
                                             @RequestParam(value = "regionCode", required = false) String regionCode,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "order", required = false) String order,
                                             @RequestParam(value = "pageToken", required = false) String pageToken,
                                             @RequestParam(value = "maxResults", required = false) String maxResults){
        try {
            JsonNode root = objectMapper.readTree(this.api.searchVideoList(channelId, queryStr, regionCode, type, order, pageToken, maxResults).block());
            List<SearchedItem> sis = new ArrayList<>();
            root.withArray("items").forEach(item -> {
                SearchedItem si = new SearchedItem();
                if(item.get("id").has("videoId")){
                    si.setVideoId(item.get("id").get("videoId").asText());
                    si.setPlaylistId("");
                } else if(item.get("id").has("playlistId")){
                    si.setPlaylistId(item.get("id").get("playlistId").asText());
                    si.setVideoId("");
                }

                if(item.get("snippet").has("description")){
                    si.setDescription(item.get("snippet").get("description").asText());
                }

                Map<String, String> thumbnails = new HashMap<>();
                item.get("snippet").get("thumbnails").fieldNames().forEachRemaining(key ->
                    thumbnails.put(key, item.get("snippet").get("thumbnails").get(key).get("url").asText())
                );
                si.setThumbnails(thumbnails);

                si.setPublishedAt(item.get("snippet").get("publishedAt").asText());
                si.setChannelId(item.get("snippet").get("channelId").asText());
                si.setChannelTitle(item.get("snippet").get("channelTitle").asText());
                si.setTitle(item.get("snippet").get("title").asText());

                sis.add(si);
            });

            SearchedPage sp = new SearchedPage();
            sp.setItems(sis);

            String prevPageToken = "";
            if(root.has("prevPageToken")){
                prevPageToken = root.get("prevPageToken").asText();
            }
            if(root.has("regionCode")){
                sp.setRegionCode(root.get("regionCode").asText());
            } else {
                sp.setRegionCode("");
            }
            sp.setPrevPageToken(prevPageToken);
            sp.setNextPageToken(root.get("nextPageToken").asText());
            sp.setTotalResults(root.get("pageInfo").get("totalResults").asInt());
            sp.setResultsPerPage(root.get("pageInfo").get("resultsPerPage").asInt());

            return sp;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return new SearchedPage();
        }
    }
}

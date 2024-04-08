package com.inrech.jobs.youtube.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrech.jobs.youtube.model.videolist.VideoPage;
import com.inrech.jobs.youtube.model.videolist.VideoSnippet;
import com.inrech.jobs.youtube.requestapi.GetList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VideoListController {
    private GetList api;
    private ObjectMapper objectMapper;

    VideoListController(){
        this.api = new GetList();
        this.objectMapper = new ObjectMapper();
    }

    // list popular videos by default
    @GetMapping("/yt/videoList")
    public VideoPage getVideoList(@RequestParam(value = "pageToken", required = false) String pageToken,
                                  @RequestParam(value = "maxResults", required = false) String maxResults,
                                  @RequestParam(value = "channelId", required = false) String channelId,
                                  @RequestParam(value = "playlistId", required = false) String playlistId){
        if(null == pageToken){
            pageToken = "";
        }

        if(null != channelId && !channelId.isEmpty()){
            return getVideoPage(this.api.searchVideoListOfChannel(channelId, pageToken, maxResults).block());
        } else if(null != playlistId && !playlistId.isEmpty()){
            return getVideoPage(this.api.getVideoListOfPlaylist(playlistId, pageToken, maxResults).block());
        }

        return getVideoPage(this.api.getVideoList(pageToken, maxResults).block());
    }

    private VideoPage getVideoPage(String responseBody){
        try {


            JsonNode root = objectMapper.readTree(responseBody);
            VideoPage vp = new VideoPage();
            List<VideoSnippet> vl = new ArrayList<>();

            root.withArray("items").forEach(item -> {
                VideoSnippet vs = new VideoSnippet();
                String videoId = "";
                if(item.get("snippet").has("resourceId")){
                    videoId = item.get("snippet").get("resourceId").asText();
                } else if(item.get("id").has("videoId")){
                    videoId = item.get("id").get("videoId").asText();
                } else if(item.get("id").isTextual() && 11 == item.get("id").asText().length()){
                    videoId = item.get("id").asText();
                }
                int viewCount = -1;
                if(item.has("statistics")) {
                    viewCount = item.get("statistics").get("viewCount").asInt();
                }

                Map<String, String> thumbnails = new HashMap<>();
                item.get("snippet").get("thumbnails").fieldNames().forEachRemaining(key ->{
                    thumbnails.put(key, item.get("snippet").get("thumbnails").get(key).get("url").asText());
                });

                vs.setVideoId(videoId);
                vs.setPublishedAt(item.get("snippet").get("publishedAt").asText());
                vs.setChannelId(item.get("snippet").get("channelId").asText());
                vs.setVideoTitle(item.get("snippet").get("title").asText());
                vs.setChannelTitle(item.get("snippet").get("channelTitle").asText());
                vs.setViewCount(viewCount);
                vs.setThumbnails(thumbnails);
                

                vl.add(vs);
            });
            vp.setVideoList(vl);

            String prevPageToken = "";
            if(root.has("prevPageToken")){
                prevPageToken = root.get("prevPageToken").asText();
            }
            String regionCode = "";
            if(root.has("regionCode")){
                regionCode = root.get("regionCode").asText();
            }
            vp.setPrevPageToken(prevPageToken);
            vp.setNextPageToken(root.get("nextPageToken").asText());
            vp.setRegionCode(regionCode);
            vp.setTotalResults(root.get("pageInfo").get("totalResults").asInt());
            vp.setResultsPerPage(root.get("pageInfo").get("resultsPerPage").asInt());

            return vp;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return new VideoPage();
        }
    }
}

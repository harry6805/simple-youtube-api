package com.inrech.jobs.youtube.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrech.jobs.youtube.model.channel.Channel;
import com.inrech.jobs.youtube.model.channel.ChannelPage;
import com.inrech.jobs.youtube.requestapi.GetList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChannelController {
    private ObjectMapper objectMapper;
    private GetList api;

    ChannelController(){
        this.api = new GetList();
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/yt/channel")
    public ChannelPage getChannel(@RequestParam(value = "channelId") String channelId){
        try {
            ChannelPage cp = new ChannelPage();
            Channel c = new Channel();

            JsonNode item = objectMapper.readTree(this.api.getChannel(channelId).block())
                    .withArray("items").get(0);

            Map<String, String> thumbnails = new HashMap<>();
            item.get("snippet").get("thumbnails").fieldNames().forEachRemaining(key ->{
                thumbnails.put(key, item.get("snippet").get("thumbnails").get(key).get("url").asText());
            });

            c.setPublishedAt(item.get("snippet").get("publishedAt").asText());
            c.setChannelId(item.get("id").asText());
            c.setChannelTitle(item.get("snippet").get("title").asText());
            c.setViewCount(item.get("statistics").get("viewCount").asInt());
            c.setSubscriberCount(item.get("statistics").get("subscriberCount").asInt());
            c.setHiddenSubscriberCount(item.get("statistics").get("hiddenSubscriberCount").asBoolean());
            c.setVideoCount(item.get("statistics").get("videoCount").asInt());
            String country = "";
            if(item.get("snippet").has("country")){
                c.setCountry(item.get("snippet").get("country").asText());
            }
            c.setThumbnails(thumbnails);

            cp.setChannel(c);
            cp.setNextPageToken("");
            cp.setPrevPageToken("");
            cp.setTotalResults(1);
            cp.setResultsPerPage(1);

            return cp;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return new ChannelPage();
        }
    }
}

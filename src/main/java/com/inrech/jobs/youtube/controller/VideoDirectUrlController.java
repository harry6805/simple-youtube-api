package com.inrech.jobs.youtube.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrech.jobs.youtube.model.directurl.Format;
import com.inrech.jobs.youtube.model.directurl.VideoDetails;
import com.inrech.jobs.youtube.model.directurl.VideoUrl;
import com.inrech.jobs.youtube.requestapi.GetVideoDirectUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VideoDirectUrlController {
    private GetVideoDirectUrl api;
    private ObjectMapper objectMapper;

    VideoDirectUrlController(){
        this.api = new GetVideoDirectUrl();
        this.objectMapper = new ObjectMapper();
    }
    @GetMapping("/yt/directurl/all")
    public VideoUrl all(@RequestParam(value = "vid") String videoId){
        String responseBody = api.post(videoId).block();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            VideoUrl yt = new VideoUrl();

            List<Format> audioVideo = new ArrayList<>();
            root.get("streamingData").withArray("formats").forEach(item -> {
                Format f = setFormat(item);
                if(f != null) {
                    audioVideo.add(f);
                }
            });
            yt.setAudioVideo(audioVideo);

            List<Format> audio = new ArrayList<>();
            List<Format> video = new ArrayList<>();
            root.get("streamingData").withArray("adaptiveFormats").forEach(item -> {
                Format f = setFormat(item);
                if(f != null){
                    if(item.get("mimeType").asText().contains("audio/")){
                        audio.add(f);
                    } else if(item.get("mimeType").asText().contains("video/")){
                        video.add(f);
                    }
                }
            });
            yt.setAudioOnly(audio);
            yt.setVideoOnly(video);

            yt.setVideoDetails(setVideoDetails(root.get("videoDetails")));

            return yt;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/yt/directurl/ao")
    public VideoUrl ao(@RequestParam(value = "vid") String videoId){
        String responseBody = api.post(videoId).block();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            VideoUrl yt = new VideoUrl();

            List<Format> audioVideo = new ArrayList<>();
            yt.setAudioVideo(audioVideo);

            List<Format> audio = new ArrayList<>();
            List<Format> video = new ArrayList<>();
            root.get("streamingData").withArray("adaptiveFormats").forEach(item -> {
                Format f = setFormat(item);
                if(f != null){
                    if(item.get("mimeType").asText().contains("audio/")){
                        audio.add(f);
                    }
                }
            });
            yt.setAudioOnly(audio);
            yt.setVideoOnly(video);
            yt.setVideoDetails(setVideoDetails(root.get("videoDetails")));

            return yt;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/yt/directurl/vo")
    public VideoUrl vo(@RequestParam(value = "vid") String videoId){
        String responseBody = api.post(videoId).block();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            VideoUrl yt = new VideoUrl();

            List<Format> audioVideo = new ArrayList<>();
            yt.setAudioVideo(audioVideo);

            List<Format> audio = new ArrayList<>();
            List<Format> video = new ArrayList<>();
            root.get("streamingData").withArray("adaptiveFormats").forEach(item -> {
                Format f = setFormat(item);
                if(f != null){
                    if(item.get("mimeType").asText().contains("video/")){
                        video.add(f);
                    }
                }
            });
            yt.setAudioOnly(audio);
            yt.setVideoOnly(video);
            yt.setVideoDetails(setVideoDetails(root.get("videoDetails")));

            return yt;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/yt/directurl/av")
    public VideoUrl av(@RequestParam(value = "vid") String videoId){
        String responseBody = api.post(videoId).block();
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            VideoUrl yt = new VideoUrl();

            List<Format> audioVideo = new ArrayList<>();
            root.get("streamingData").withArray("formats").forEach(item -> {
                Format f = setFormat(item);
                if(f != null) {
                    audioVideo.add(f);
                }
            });
            yt.setAudioVideo(audioVideo);

            List<Format> audio = new ArrayList<>();
            List<Format> video = new ArrayList<>();
            yt.setAudioOnly(audio);
            yt.setVideoOnly(video);

            yt.setVideoDetails(setVideoDetails(root.get("videoDetails")));

            return yt;
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    private Format setFormat(JsonNode format){
        Format f = new Format();
        /*
        if(!format.has("averageBitrate")
                || format.get("averageBitrate").asText().isEmpty()
                || format.get("averageBitrate").asInt() == 0) {
            return null;
        }*/
        f.setItag(format.get("itag").asInt());
        f.setUrl(format.get("url").asText());
        f.setMimeType(format.get("mimeType").asText());
        f.setBitrate(format.get("bitrate").asInt());
        f.setApproxDurationMs(format.get("approxDurationMs").asInt());
        if(format.has("averageBitrate")
                && !format.get("averageBitrate").asText().isEmpty()
                && 0 != format.get("averageBitrate").asInt()) {
            f.setAverageBitrate(format.get("averageBitrate").asInt());
        }

        return f;
    }
    private VideoDetails setVideoDetails(JsonNode videoDetailsNode){
        VideoDetails videoDetails = new VideoDetails();
        videoDetails.setVideoId(videoDetailsNode.get("videoId").asText());
        videoDetails.setVideoTitle(videoDetailsNode.get("title").asText());
        videoDetails.setAuthor(videoDetailsNode.get("author").asText());
        videoDetails.setChannelId(videoDetailsNode.get("channelId").asText());

        List<Map<String, String>> thumbnails = new ArrayList<>();
        Map<String, String> thumbnail = new HashMap<>();
        videoDetailsNode.get("thumbnail").withArray("thumbnails").forEach(item ->{
            item.fieldNames().forEachRemaining(key -> {
                thumbnail.put(key, item.get(key).asText());
            });
            thumbnails.add(thumbnail);
        });
        videoDetails.setThumbnails(thumbnails);

        videoDetails.setViewCount(videoDetailsNode.get("viewCount").asLong());
        videoDetails.setLengthSeconds(videoDetailsNode.get("lengthSeconds").asInt());
        videoDetails.setShortDescription(videoDetailsNode.get("shortDescription").asText());
        return videoDetails;
    }
}

package com.inrech.jobs.youtube.controller;

import com.inrech.jobs.youtube.database.Video;
import com.inrech.jobs.youtube.database.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/userdata/videos")
public class UserVideoController {
    @Autowired
    private VideoRepository videoRepository;

    @PostMapping(path="/add")
    public @ResponseBody Map<String,String> addFavorites(@RequestBody Video v){
        // to-do
        //check Video entity
        //check userId
        videoRepository.save(v);
        Map<String,String> re = new HashMap<>();
        re.put("videoTitle", v.getVideoTitle());
        re.put("userId", v.getUser().getId().toString());
        re.put("id", v.getId().toString());
        return re;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Video> getAllVideos(@RequestParam String userId){
        // to-do
        // check if userId is valid
        return videoRepository.findAllVideos(Integer.parseInt(userId));

         //return "success " + videos.iterator().next().getUser().getEmail();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Map<String,String> deleteVideo(@RequestParam String userId, @RequestParam String videoId){
        // to-do
        //check userId and channelId
        videoRepository.deleteVideo(Integer.parseInt(userId), videoId);
        Map<String,String> re = new HashMap<>();
        re.put("userId", userId);
        re.put("id", videoId);
        return re;
    }

    @PostMapping(path = "/update_favorite")
    public @ResponseBody Map<String,String> updateFavorite(@RequestBody Video v){
        // to do
        // check Video
        if(null == videoRepository.findByVideoId(v.getUser().getId(), v.getVideoId())){
            videoRepository.save(v);
        } else {
            videoRepository.updateVideoFavorite(v.getUser().getId(), v.getVideoId(), v.isFavored());
        }
        Map<String,String> re = new HashMap<>();
        re.put("userId", v.getUser().getId().toString());
        re.put("videoId", v.getVideoId());
        return re;
    }

    @PostMapping(path = "/update_playback")
    public @ResponseBody Map<String,String> updatePlayback(@RequestBody Video v){
        // to do
        // check Video
        if(!Integer.toString(v.getResumeAt()).isEmpty() && v.getResumeAt() > 0){
            if (null == videoRepository.findByVideoId(v.getUser().getId(), v.getVideoId())) {
                videoRepository.save(v);
            } else {
                videoRepository.updateVideoPlaybackPosition(v.getUser().getId(), v.getVideoId(), v.getResumeAt());
            }
        }
        Map<String,String> re = new HashMap<>();
        re.put("userId", v.getUser().getId().toString());
        re.put("videoId", v.getVideoId());
        return re;
    }
}

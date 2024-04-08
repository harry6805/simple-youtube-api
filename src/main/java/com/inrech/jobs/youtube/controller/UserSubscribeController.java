package com.inrech.jobs.youtube.controller;

import com.inrech.jobs.youtube.database.Subscribe;
import com.inrech.jobs.youtube.database.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/userdata/subscribe")
public class UserSubscribeController {
    @Autowired
    private SubscribeRepository subscribeRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody Map<String,String> addSubscribe(@RequestBody Subscribe s){
        // to-do
        //check Subscribe entity
        //check user and userId
        subscribeRepository.save(s);
        Map<String,String> re = new HashMap<>();
        re.put("channelId", s.getChannelId());
        re.put("channelTitle", s.getChannelTitle());
        re.put("user_id", s.getUser().getId().toString());
        return re;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody Iterable<Subscribe> getAllSubscribes(@RequestParam String userId){
        return subscribeRepository.getAllSubscribes(Integer.parseInt(userId));
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody Map<String,String> deleteFavorites(@RequestParam String userId, @RequestParam String channelId){
        // to-do
        //check userId and channelId
        subscribeRepository.deleteChannel(Integer.parseInt(userId), channelId);
        Map<String,String> re = new HashMap<>();
        re.put("userId", userId);
        re.put("channelId", channelId);
        return re;
    }
}

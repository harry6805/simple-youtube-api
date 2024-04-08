package com.inrech.jobs.youtube.controller;

import com.inrech.jobs.youtube.database.User;
import com.inrech.jobs.youtube.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add", consumes = {"application/json"}, produces = {"application/json"})
    public @ResponseBody Map<String,String> addUser(@RequestBody User u){
        userRepository.save(u);
        Map<String,String> re = new HashMap<>();
        re.put("username", u.getName());
        re.put("email", u.getEmail());
        re.put("id", u.getId().toString());
        return re;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Map<String,String> getAllUser(@PathVariable String id){
        // to-do
        // check if userId is int
        Optional<User> user = userRepository.findById(Integer.parseInt(id));
        Map<String,String> re = new HashMap<>();
        if(user.isPresent()) {
            re.put("username", user.get().getName());
            re.put("email", user.get().getEmail());
        }
        return re;
    }
}

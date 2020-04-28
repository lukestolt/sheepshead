package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.User;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private Gson gson = new Gson();

    @PostMapping("/createUser")
    public String createUser(@RequestBody String name){
        return gson.toJson(new User());
    }
}

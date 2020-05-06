package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.User;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private Gson gson = new Gson();

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody String name){
        return gson.toJson(new User());
    }
}

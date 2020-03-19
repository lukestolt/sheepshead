package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.GamesManager;
import com.capstone.sheepsheadbackend.model.Player;
import com.capstone.sheepsheadbackend.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private User user1 = new User();
    private User user2 = new User();
    private User user3 = new User();
    private User user4 = new User();
    private User user5 = new User();
    private User user6 = new User();
    private User user7 = new User();
    private User user8 = new User();
    private User user9 = new User();
    private User user10 = new User();
    private User user11= new User();

    GamesManager gm = new GamesManager();


    @GetMapping("/addPlayer")
    public void addPlayer() {
        gm.addPlayer(new Player(user1));
        System.out.println(gm.getWait());
        System.out.println(gm.getGames());
    }
}

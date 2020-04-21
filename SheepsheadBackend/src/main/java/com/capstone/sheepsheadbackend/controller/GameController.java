package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.controller.game.PlayCardResponse;
import com.capstone.sheepsheadbackend.model.GamesManager;
import com.capstone.sheepsheadbackend.model.Player;
import com.capstone.sheepsheadbackend.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
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

    @Autowired
    private SimpMessagingTemplate messageSender;


    @GetMapping("/addPlayer")
    public void addPlayer() {
        gm.addPlayer(new Player(user1));
        System.out.println(gm.getWait());
        System.out.println(gm.getGames());
    }

    @PostMapping("/sendPlayerAction")
    public String sendPlayerAction(@RequestBody Action action) {
        // tell the people subscribed to the game that someone did a player action
        // all the clients should update
        // the game logic should be here to create the response
        PlayCardResponse res = new PlayCardResponse("p2", action.gameId, 1, action.suit, action.value);
        messageSender.convertAndSend("/topic/game", res.createResponse());
        return new Gson().toJson("Hi");
    }

    // if something is sent to the /game destination then gameCommunication() is called
    @MessageMapping("/gameaction")
    @SendTo("topic/game")
    public String greeting(@Payload String name) throws Exception{
        int x = 0;
        System.out.println("here on server");
        Thread.sleep(2000);
        return(name + " sent a message");
    }
}

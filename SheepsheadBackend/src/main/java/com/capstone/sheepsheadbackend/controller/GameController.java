package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.controller.game.FindGameRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        // TODO: return the new array of cards for the player that just played the card and send the updated current trick
        // this data should come from the game
        return new Gson().toJson("Hi");
    }

    // if something is sent to the /game destination then gameCommunication() is called
    @MessageMapping("/gameaction")
    @SendTo("topic/game")
    public String gameCommuncation(@Payload String name) throws Exception{
        int x = 0;
        System.out.println("here on server");
        Thread.sleep(2000);
        return(name + " sent a message");
    }

    /**
     * @return the gameId
     */
    @PostMapping(value = "/findGame")
    public String findGame(@RequestBody FindGameRequest req) {
        // TODO: get the game id from the game manager?
        System.out.println(req.playerId);
        String gameID = "rand-game-id-from-server";
        return new Gson().toJson(gameID);
    }

    /**
     *
     * the client should subscribe to this to tell the client that the game is ready
     */
    public String gameStatus(){

        return null;
    }

    /**
     * send back the players cards and num cards the opponents have or it can be assumed
     * @param playerId
     * @return
     */
    @GetMapping("/sendGameInfo")
    public String sendGameInformation(@RequestBody String playerId) {
        return null;
    }
}

package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.controller.game.FindGameRequest;
import com.capstone.sheepsheadbackend.controller.game.PlayCardResponse;
import com.capstone.sheepsheadbackend.model.GamesManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class GameController {

    GamesManager gm = new GamesManager();
    Gson gson = new Gson();


////TEMP GAME DATA
    String gameID = "rand-game-id-from-server";
    SimpleCard c1 = new SimpleCard("S","10");
    SimpleCard c2 = new SimpleCard("C","Q");
    SimpleCard c3 = new SimpleCard("H","A");
    SimpleCard c4 = new SimpleCard("D","7");
    ArrayList<SimpleCard> cards = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
////

    @Autowired
    private SimpMessagingTemplate messageSender;

    /**
     * this client should call this method via http so it can get an easier response and dont
     * have to create a subscription for the caller
     * all the other clients will be updated via the subscription
     * @param action
     * @return
     */
    @PostMapping("/gameAction")
    public String gameAction(@RequestBody Action action) {
        // tell the people subscribed to the game that someone did a player action
        // all the clients should update
        // the game logic should be here to create the response
        SimpleCard actionCard = new SimpleCard(action.suit, action.value);
        //TODO: this should be removed when game logic integrated
        cards.remove(actionCard);
        PlayCardResponse res = new PlayCardResponse("p2", action.gameId, this.cards.toArray());
        messageSender.convertAndSend("/topic/gameData", res.createResponse());
        // TODO: return the new array of cards for the player that just played the card and send the updated current trick
        return this.gson.toJson(res);
    }

//    @MessageMapping("/gameaction")
//    public String gameCommuncation(@Payload String name) throws Exception{
//        int x = 0;
//        System.out.println("here on server");
//        Thread.sleep(2000);
//        return(name + " sent a message");
//    }

    /**
     * @return the gameId
     */
    @PostMapping(value = "/findGame")
    public String findGame(@RequestBody FindGameRequest req) {
        // TODO: get the game id from the game manager?
        System.out.println(req.playerId);
        return this.gson.toJson(this.gameID);
    }

    /**
     * call this when the game is full and ready to be played
     * the client should subscribe to this to tell the client that the game is ready
     */
    // FIXME: remove the annotation because the game manager should call this game when its ready
    @GetMapping("/gamestatus")
    public String gameStatusReady(){
        messageSender.convertAndSend("/topic/gamestatus/" + this.gameID, "ready");
        return this.gson.toJson("ready");
    }

    public class SimpleCard{
        public String suit;
        public String value;
        SimpleCard(String suit, String value) {
            this.suit = suit;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if( o instanceof SimpleCard) {
                SimpleCard card = (SimpleCard)o;
                return (this.suit.equals(card.suit) && this.value.equals(card.value));
            }
            return false;
        }
    }
    /**
     * send back the players cards
     * @param playerId
     * @return players hand
     */
    @GetMapping("/getHand")
    public String getHand(@RequestParam String playerId, String gameId) {
        // TODO: get the game and game.getHand()
        int handSize = 4;
        return this.gson.toJson(cards.toArray());
    }
}

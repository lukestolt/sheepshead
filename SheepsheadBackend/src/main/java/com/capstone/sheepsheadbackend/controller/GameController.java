package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.controller.game.AbstractResponse;
import com.capstone.sheepsheadbackend.controller.game.FindGameRequest;
import com.capstone.sheepsheadbackend.controller.game.PlayCardResponse;
import com.capstone.sheepsheadbackend.model.GamesManager;
import com.capstone.sheepsheadbackend.model.Player;
import com.capstone.sheepsheadbackend.model.User;
import com.capstone.sheepsheadbackend.model.actions.Action;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class GameController {

//    private User user1 = new User();
//    private User user2 = new User();
//    private User user3 = new User();
//    private User user4 = new User();
//    private User user5 = new User();
//    private User user6 = new User();
//    private User user7 = new User();
//    private User user8 = new User();
//    private User user9 = new User();
//    private User user10 = new User();
//    private User user11= new User();

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

    @GetMapping("/addPlayer")
    public void addPlayer() {
//        gm.addPlayer(new Player(user1));
//        System.out.println(gm.getWait());
//        System.out.println(gm.getGames());
    }

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

        AbstractResponse response = gm.addAction(action);
//        SimpleCard actionCard = new SimpleCard(action.suit, action.value);
        //TODO: this should be removed when game logic integrated
//        cards.remove(actionCard);
//        PlayCardResponse res = new PlayCardResponse("p2", action.gameId, this.cards.toArray());
//        messageSender.convertAndSend("/topic/gameData", res.createResponse());
        // TODO: SendPlayer onVALID: hand
        //       SendPLayer onERROR:

        // TODO: Broadcast onVALID: send the updated current trick, playerId of card player, hand size of card player
        //       Brodcast  onERROR: NOTHING
//        return this.gson.toJson(res);
        return null;
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

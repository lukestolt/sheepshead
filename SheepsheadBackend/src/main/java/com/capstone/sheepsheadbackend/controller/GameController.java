package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.response.AbstractResponse;
import com.capstone.sheepsheadbackend.controller.game.FindGameRequest;
import com.capstone.sheepsheadbackend.model.GamesManager;
import com.capstone.sheepsheadbackend.model.actions.PlayCardAction;
import com.capstone.sheepsheadbackend.model.response.PlayCardResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    GamesManager gm = GamesManager.getInstance();
    Gson gson = new Gson();
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
    public String gameAction(@RequestBody PlayCardAction action) {
        // tell the people subscribed to the game that someone did a player action
        // all the clients should update
        // the game logic should be here to create the response

        AbstractResponse response = gm.addAction(action);
        PlayCardResponse pca = null;
        if(response instanceof PlayCardResponse) {
            PlayCardResponse p = (PlayCardResponse)response;
            pca = new PlayCardResponse(response.playerId, response.gameId, null, p.getNextTurnId(), p.getTrick());
        }

//        SimpleCard actionCard = new SimpleCard(action.suit, action.value);
        //TODO: this should be removed when game logic integrated
//        cards.remove(actionCard);
//        PlayCardResponse res = new PlayCardResponse("p2", action.gameId, this.cards.toArray());
//        System.out.println(response.createResponse());
//        AbstractResponse broadcastResponse = response;
//        if(broadcastResponse instanceof PlayCardResponse) {
//            PlayCardResponse pcp = (PlayCardResponse)broadcastResponse;
//            pcp.setCards(null);
//            broadcastResponse = pcp;
//        }
        if(pca != null) {
            messageSender.convertAndSend("/topic/actionResponse", pca.createResponse());
        }
        // TODO: SendPlayer onVALID: hand
        //       SendPLayer onERROR:

        // TODO: Broadcast onVALID: send the updated current trick, playerId of card player, hand size of card player
        //       Brodcast  onERROR: NOTHING

        return this.gson.toJson(response);
//        return null;
    }

    /**
     * @return the gameId
     */
    @PostMapping(value = "/findGame")
    public String findGame(@RequestBody FindGameRequest req) {
        // needs to have the message sender via this way
        gm.setMessageSender(this.messageSender);
        String gId = gm.addPlayer(req.playerId, req.username);
        return this.gson.toJson(gId);
    }

    /**
     * this is called when the player is all set up and connected
     * @param gId
     */
    @PostMapping("/playerReady")
    public void playerReady(@RequestBody String gId) {
        System.out.println("Player Ready");
        this.gm.broadcastInitGameInfo(gId);
    }




}

package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.actions.BlindAction;
import com.capstone.sheepsheadbackend.model.response.*;
import com.capstone.sheepsheadbackend.model.game.FindGameRequest;
import com.capstone.sheepsheadbackend.model.GamesManager;
import com.capstone.sheepsheadbackend.model.actions.PlayCardAction;
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
     * Handler for finding games. Creates player and adds them to a game. Returns Game ID.
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
     * this client should call this method via http so it can get an easier response and dont
     * have to create a subscription for the caller
     * all the other clients will be updated via the subscription
     * @param action Action that user wishes to perform
     * @return Response of game to that action
     */
    @PostMapping("/gameAction")
    public String gameAction(@RequestBody PlayCardAction action) {
        // tell the people subscribed to the game that someone did a player action
        // all the clients should update
        // the game logic should be here to create the response

        AbstractResponse response = gm.addAction(action);
        if(response instanceof PlayCardResponse) {
            PlayCardResponse p = (PlayCardResponse)response;
            PlayCardResponse pca = new PlayCardResponse(response.playerId, response.gameId, null, p.getNextTurnId(), p.getTrick());
            messageSender.convertAndSend("/topic/actionResponse/" + response.gameId, pca.createResponse());
        }
        else if(response instanceof WinningGameResponse){
            WinningGameResponse wgr = (WinningGameResponse)response;
            messageSender.convertAndSend("/topic/actionResponse/" + response.gameId, wgr.createResponse());
            // need to return the trick to the player still
            PlayCardResponse pcr = new PlayCardResponse(response.playerId, response.gameId, null, null, null);
            return pcr.createResponse();
        }
        return response.createResponse();
    }

    /**
     * Server handler to handle picking the blind.
     * @param action BlindAction with the details of who wants to take the blind
     */
    @PostMapping("/gameBlindAction")
    public void gameAction(@RequestBody BlindAction action) {
        action.convertCards();
        // should send back the player their correct cards even if they accept or decline the blind;
        // need to remove the blind which are the last two cards from their hand if they decline
        // if they accept remove the blind[] of cards from the their hand
        AbstractResponse response = gm.addAction(action);
        // update all the players just like in the beginning of the game
        gm.broadcastInitGameInfo(action.getGameId());
        // only broadcast the blindresponse when it was accepted to tell client that the blindstate is over
        if(response != null){
            AcceptBlindResponse abr = (AcceptBlindResponse)response;
            messageSender.convertAndSend("/topic/actionResponse/" + response.gameId, abr.createResponse());
        }
    }

    /**
     * this is called when the player is all set up and connected
     * @param gId
     */
    @PostMapping("/playerReady")
    public void playerReady(@RequestBody String gId) {
        this.gm.broadcastInitGameInfo(gId);
    }

}

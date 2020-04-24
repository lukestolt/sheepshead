package com.capstone.sheepsheadbackend.controller.game;

import com.google.gson.Gson;

/**
 * Tell the subscribers that this player has x cards now and what card was played
 */
public class PlayCardResponse {
    Object[] cards;
    String playerId;
    String gameId;


    public PlayCardResponse(String playerId, String gameId, Object[] cards) {
        this.playerId = playerId;
        this.gameId = gameId;
        // TODO: convert the cards to simple cards to be sent to the
        this.cards = cards;
    }

    public String createResponse() {
        String s = new Gson().toJson(this);
        System.out.println(s);
        return s;
    }
}

package com.capstone.sheepsheadbackend.controller.game;

import com.google.gson.Gson;

/**
 * Tell the subscribers that this player has x cards now and what card was played
 */
public class PlayCardResponse extends AbstractResponse {
    String actionType;
    int numCards;
    String suit;
    String value;

    public PlayCardResponse(String playerId, String gameId, int numCards, String suit, String value) {
        super(playerId, gameId, "PlayCardResponse");
        this.numCards = numCards;
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String createResponse() {
        String s = new Gson().toJson(this);
        System.out.println(s);
        return s;
    }
}

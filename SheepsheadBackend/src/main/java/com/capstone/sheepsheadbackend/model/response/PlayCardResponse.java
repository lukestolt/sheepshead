package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.capstone.sheepsheadbackend.model.Player;
import com.google.gson.Gson;

import java.util.List;

/**
 * Tell the subscribers that this player has x cards now and what card was played
 */
public class PlayCardResponse extends AbstractResponse{
    List<Card> cards;
    int handSize;
    Player nextTurn;

    public PlayCardResponse(String playerId, String gameId, List<Card> cards, Player nextTurn) {
        super(playerId, gameId, "validCard");
        this.playerId = playerId;
        this.gameId = gameId;
        this.cards = cards;
        this.handSize = cards.size();
        this.nextTurn = nextTurn;
    }

    public String createResponse() {
        String s = new Gson().toJson(this);
        System.out.println(s);
        return s;
    }
}

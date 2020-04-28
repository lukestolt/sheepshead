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
    String nextTurnId;
    List<Card> trick;

    public PlayCardResponse(String playerId, String gameId, List<Card> cards, String nextTurnId, List<Card> trick) {
        super(playerId, gameId, "validCard");
        this.playerId = playerId;
        this.gameId = gameId;
        this.cards = cards;
        if (cards == null) {
            this.handSize = -1;
        } else {
            this.handSize = cards.size();
        }
        this.nextTurnId = nextTurnId;
        this.trick = trick;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getNextTurnId() {
        return nextTurnId;
    }

    public List<Card> getTrick() {
        return trick;
    }

    public String createResponse() {
        String s = new Gson().toJson(this);
        System.out.println(s);
        return s;
    }
}

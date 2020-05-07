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

    /**
     * Create a response to playing a card
     * @param playerId Player Id of response
     * @param gameId Game Id of response
     * @param cards New Cards of Player
     * @param nextTurnId Player Id of next Player
     * @param trick Updated Trick
     */
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

    /**
     * Set cards to given cards
     * @param cards Cards to set
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Get Player Id of next player
     * @return Player Id of next player
     */
    public String getNextTurnId() {
        return nextTurnId;
    }

    /**
     * Get current trick
     * @return Current trick
     */
    public List<Card> getTrick() {
        return trick;
    }

    @Override
    public String createResponse() {
        String s = new Gson().toJson(this);
        return s;
    }
}

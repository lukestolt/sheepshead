package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;
import com.fasterxml.jackson.annotation.JsonRootName;

public class PlayCardAction extends Action {
    private String suit;
    private String value;

    /**
     * Create new Play Card Action
     */
    public PlayCardAction() {
        super();
    }

    /**
     * Create new Play Card Action
     * @param action Action as String
     * @param playerId Player Id of action
     * @param gameId Game Id of action
     * @param suit Suit of Card Played
     * @param value Value of Card Played
     */
    public PlayCardAction(String action, String playerId, String gameId, String suit, String value) {
        super(action, playerId, gameId);
        this.suit = suit;
        this.value = value;
    }

    /**
     * Get Suit of card played
     * @return Suit of card played
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Get value of card played
     * @return value of card played
     */
    public String getValue() {
        return value;
    }

    /**
     * Set suit of card
     * @param suit Suit to set
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Set value of card
     * @param value Value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void perform(GamesManager gm) {
        gm.addAction(this);
    }
}

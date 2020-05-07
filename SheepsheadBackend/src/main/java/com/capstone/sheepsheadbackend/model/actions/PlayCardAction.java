package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;
import com.fasterxml.jackson.annotation.JsonRootName;

public class PlayCardAction extends Action {
    private String suit;
    private String value;

    /**
     *
     */
    public PlayCardAction() {
        super();
    }

    /**
     *
     * @param action
     * @param playerId
     * @param gameId
     * @param suit
     * @param value
     */
    public PlayCardAction(String action, String playerId, String gameId, String suit, String value) {
        super(action, playerId, gameId);
        this.suit = suit;
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getSuit() {
        return suit;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void perform(GamesManager gm) {
        gm.addAction(this);
    }
}

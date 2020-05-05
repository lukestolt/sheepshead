package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;
import com.fasterxml.jackson.annotation.JsonRootName;

public class PlayCardAction extends Action {
    private String suit;
    private String value;

    public PlayCardAction() {
        super();
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PlayCardAction(String action, String playerId, String gameId, String suit, String value) {
        super(action, playerId, gameId);
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void perform(GamesManager gm) {
        gm.addAction(this);
    }
}

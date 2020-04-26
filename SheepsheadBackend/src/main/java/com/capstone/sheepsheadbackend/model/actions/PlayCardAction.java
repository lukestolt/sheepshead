package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;

public class PlayCardAction extends Action {
    String suit;
    String value;

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

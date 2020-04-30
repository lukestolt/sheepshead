package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.Card;
import com.capstone.sheepsheadbackend.model.GamesManager;

import java.util.List;

public class BlindAction extends Action{
    boolean isAcceptBlind;
    // null if declined or of size 2 if accepted
    Card[] buriedCards;

    public BlindAction( String gameId, String playerId, boolean acceptBlind, Card[] buriedCards){
        super("BlindAction", gameId,playerId);
        this.isAcceptBlind = acceptBlind;
        this.buriedCards = buriedCards;
    }

    @Override
    public void perform(GamesManager gm) {

    }
}

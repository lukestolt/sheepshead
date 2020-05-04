package com.capstone.sheepsheadbackend.model.actions;
import com.capstone.sheepsheadbackend.model.Card;
import com.capstone.sheepsheadbackend.model.GamesManager;

public class BlindAction extends Action {
    // should only be the size of 2 since the game only allows 3 players
    public Card[] burriedCards;

    public BlindAction(String gameId, String playerId, String actionType, Card[] burriedCards){
        super(actionType, gameId, playerId);
        this.burriedCards = burriedCards;
    }

    @Override
    public void perform(GamesManager gm) {
        gm.addAction(this);
    }
}

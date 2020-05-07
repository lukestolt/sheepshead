package com.capstone.sheepsheadbackend.model.game;

import com.capstone.sheepsheadbackend.model.Card;

import java.util.List;

public class InitGameData {

    public List<Card> cards;
    public String turnPlayerId;

    /**
     * Inital Game data used to begin creating a game
     * @param id Player Id of player whose turn it is
     * @param cards Set of cards
     */
    public InitGameData(String id, List<Card> cards){
        this.cards = cards;
        this.turnPlayerId = id;
    }

}

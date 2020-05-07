package com.capstone.sheepsheadbackend.model.game;

import com.capstone.sheepsheadbackend.model.Card;

import java.util.List;

public class InitGameData {

    public List<Card> cards;
    public String turnPlayerId;

    /**
     *
     * @param id
     * @param cards
     */
    public InitGameData(String id, List<Card> cards){
        this.cards = cards;
        this.turnPlayerId = id;
    }

}

package com.capstone.sheepsheadbackend.controller.game;

import com.capstone.sheepsheadbackend.model.Card;

import java.util.List;

public class InitGameData {

    public List<Card> cards;
    public String turnPlayerId;

    public InitGameData(String id, List<Card> cards){
        this.cards = cards;
        this.turnPlayerId = id;
    }

}

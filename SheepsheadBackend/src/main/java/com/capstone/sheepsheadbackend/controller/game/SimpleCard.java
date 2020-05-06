package com.capstone.sheepsheadbackend.controller.game;

import com.capstone.sheepsheadbackend.model.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleCard {

    String suit;
    String value;

    public SimpleCard(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    public SimpleCard(){

    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

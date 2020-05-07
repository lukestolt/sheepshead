package com.capstone.sheepsheadbackend.model.game;

public class SimpleCard {

    String suit;
    String value;

    /**
     *
     * @param suit
     * @param value
     */
    public SimpleCard(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    /**
     *
     */
    public SimpleCard(){

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
     * @param suit
     */
    public void setSuit(String suit) {
        this.suit = suit;
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
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}

package com.capstone.sheepsheadbackend.model.game;

public class SimpleCard {

    String suit;
    String value;

    /**
     * Create a new Simple Card made of just suit and value as Strings
     * @param suit String suit of card
     * @param value String value of card
     */
    public SimpleCard(String suit, String value){
        this.suit = suit;
        this.value = value;
    }

    /**
     * Create a new Simple Card
     */
    public SimpleCard(){

    }

    /**
     * Get suit of card
     * @return Suit of card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Set suit of card to given suit
     * @param suit Suit of card to set to
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Get value of card
     * @return Value of card
     */
    public String getValue() {
        return value;
    }

    /**
     * Set value of card to given value
     * @param value Value of card to set to
     */
    public void setValue(String value) {
        this.value = value;
    }
}

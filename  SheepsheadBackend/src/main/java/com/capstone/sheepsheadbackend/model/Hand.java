package com.capstone.sheepsheadbackend.model;

public class Hand {
    private Card[] hand;

    public Hand() {
        hand = new Card[6];
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] cards) {
        hand = cards;
    }

}

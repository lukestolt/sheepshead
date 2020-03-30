package com.capstone.sheepsheadbackend.model;

public class Hand {
    private Card[] hand;

    public Hand(int numPlayers) {
        hand = new Card[SheepsheadDeck.DECKSIZE/numPlayers];
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] cards) {
        hand = cards;
    }

}

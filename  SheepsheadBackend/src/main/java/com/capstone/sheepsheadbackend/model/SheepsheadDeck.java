package com.capstone.sheepsheadbackend.model;

public class SheepsheadDeck {

    private static final String[] VALUES = new String[]{"7","8","9","10","J","Q","K","A"};
    private static final String[] SUITS = new String[]{"Hearts","Clubs","Diamonds","Spades"};

    private Card[] deck;

    public SheepsheadDeck() {
        deck = new Card[32];
        Deck.initDeck(deck, VALUES, SUITS);
    }

    public Card[] getDeck() {
        return deck;
    }

    public void shuffle() {
        Deck.shuffle(deck);
    }
}

package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.CardSuits;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

public class SheepsheadDeck {

    private Card[] deck;

    public SheepsheadDeck() {
        deck = new Card[32];
        Deck.initDeck(deck, SheepsheadCardValue.values(), CardSuits.values());
    }

    public Card[] getDeck() {
        return deck;
    }

    public void shuffle() {
        Deck.shuffle(deck);
    }
}

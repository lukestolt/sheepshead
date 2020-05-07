package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;
    private int MAX_CAP;

    /**
     * Create Hand for a game with the given number of players
     * @param numPlayers
     */
    public Hand(int numPlayers) {
        MAX_CAP = SheepsheadDeck.DECKSIZE/numPlayers;
        hand = new ArrayList<>(MAX_CAP);
    }

    /**
     * Create Empty Hand
     */
    public Hand(){
        hand = new ArrayList<>();
    }

    /**
     * Get cards in this hand
     * @return Cards in the hand
     */
    public List<Card> getCards() {
        return hand;
    }

    /**
     * Add a card to the hand
     * @param c Card to add to the ahnd
     */
    public void addCard(Card c) {
        hand.add(c);
    }
}

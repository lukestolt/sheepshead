package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;
    private int MAX_CAP;

    /**
     *
     * @param numPlayers
     */
    public Hand(int numPlayers) {
        MAX_CAP = SheepsheadDeck.DECKSIZE/numPlayers;
        hand = new ArrayList<>(MAX_CAP);
    }

    /**
     *
     */
    public Hand(){
        hand = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public List<Card> getCards() {
        return hand;
    }

    /**
     *
     * @param c
     */
    public void addCard(Card c) {
        hand.add(c);
    }
}

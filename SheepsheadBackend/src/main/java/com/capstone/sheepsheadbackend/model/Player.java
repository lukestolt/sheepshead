package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private User user;
    private Hand hand;
    private List<Trick> tricks;
    private int score = 0;

    public Player(User user) {
        this.user = user;
        tricks = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void wonTrick(Trick trick) {
        tricks.add(trick);
        score += trick.getScore();
    }

    @Override
    public String toString() {
        return user.toString();
    }

    public Card playCard(Card card) {
        for(int i = 0; i < hand.getCards().size(); i++) {
            if(hand.getCards().get(i).equals(card)) {
                // check followSuit
                hand.getCards().remove(i);
                return card;
            }
        }
        // if bad
        return null;
    }

    public int getScore() {
        return score;
    }

    public boolean hasCards() {
        return !hand.getCards().isEmpty();
    }

    public Card getCard(String suit, String value) {
        for (Card c: hand.getCards()) {
            if(c.equals(new Card(suit,value))) return c;
        }
        throw new IllegalStateException("Card not found in players hand");
    }

//    public String getUpid() {
//        return upid;
//    }
}

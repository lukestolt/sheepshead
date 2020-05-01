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
        hand = new Hand();
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

    public Card playCard(Card card, boolean followSuitTrump, String followSuit) {
        System.out.println("card: " + card.toString() + " followSuitTrump: " + followSuitTrump + " / followSuit: " + followSuit);
        System.out.println(cardFollowsSuit(card, followSuitTrump, followSuit));

        if(followSuitTrump){
            if(hasTrump()) {
                if(card.isTrumpSuit()) {
                    for (int i = 0; i < hand.getCards().size(); i++) {
                        if (hand.getCards().get(i).equals(card)) {
                            // check followSuitTrump
                            hand.getCards().remove(i);
                            return card;
                        }
                    }
                }
                return null;
            } else {
                for (int i = 0; i < hand.getCards().size(); i++) {
                    if (hand.getCards().get(i).equals(card)) {
                        // check followSuitTrump
                        hand.getCards().remove(i);
                        return card;
                    }
                }
            }
        } else {
            if(hasSuit(followSuit)) {
                if(card.getSuit().equals(followSuit)) {
                    for (int i = 0; i < hand.getCards().size(); i++) {
                        if (hand.getCards().get(i).equals(card)) {
                            // check followSuitTrump
                            hand.getCards().remove(i);
                            return card;
                        }
                    }
                }
            } else {
                for (int i = 0; i < hand.getCards().size(); i++) {
                    if (hand.getCards().get(i).equals(card)) {
                        // check followSuitTrump
                        hand.getCards().remove(i);
                        return card;
                    }
                }
            }
        }

//        if(canFollowSuit(followSuitTrump, followSuit) && cardFollowsSuit(card, followSuitTrump, followSuit)) {
//            for (int i = 0; i < hand.getCards().size(); i++) {
//                if (hand.getCards().get(i).equals(card)) {
//                    // check followSuitTrump
//                    hand.getCards().remove(i);
//                    return card;
//                }
//            }
//        }
        // if bad
        return null;
    }

    private boolean canFollowSuit(boolean followSuitTrump, String followSuit) {
        if(followSuitTrump) {
            return true;
        } else {
            if(hasTrump()) return true;
            return hasSuit(followSuit);
        }
    }

    private boolean hasSuit(String suit) {
        for(Card c: hand.getCards()) {
            if(c.getSuit().equals(suit)) return true;
        }
        return false;
    }

    private boolean hasTrump() {
        for(Card c: hand.getCards()) {
            if(c.isTrumpSuit()) return true;
        }
        return false;
    }

    private boolean cardFollowsSuit(Card c, boolean followSuitTrump, String followSuit) {
        if(followSuitTrump) {
            return c.isTrumpSuit();
        } else {
            return c.getSuit().equals(followSuit);
        }
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
}

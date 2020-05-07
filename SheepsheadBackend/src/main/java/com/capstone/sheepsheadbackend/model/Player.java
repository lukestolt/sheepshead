package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Player {
    private User user;
    private Hand hand;
    private List<Trick> tricks;
    private int score = 0;

    /**
     *
     * @param user
     */
    public Player(User user) {
        this.user = user;
        tricks = new ArrayList<>();
        hand = new Hand();
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @return
     */
    public Hand getHand() {
        return hand;
    }

    /**
     *
     * @return
     */
    public int getNumTricks(){return this.tricks.size();};

    /**
     *
     * @param hand
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     *
     * @param cards
     */
    public void giveBlind(List<Card> cards){
        this.getHand().addCard(cards.get(0));
        this.getHand().addCard(cards.get(1));
    }

    /**
     *
     * @param blind
     */
    public void removeBlind(List<Card> blind){
        Iterator iterator = this.getHand().getCards().iterator();
        while(iterator.hasNext()){
            Card c = (Card)iterator.next();
            if(c.equals(blind.get(0)) || c.equals(blind.get(1))){
                iterator.remove();
            }
        }

    }

    /**
     *
     * @param trick
     */
    public void wonTrick(Trick trick) {
        tricks.add(trick);
        score += trick.getScore();
    }

    /**
     * removes the burried cards from the players hand
     * @param trick
     * @return the new hand of the player
     */
    public List<Card> burryCards(Trick trick){
        this.wonTrick(trick);
        // remove the cards from the players hand
        List<Card> cards = trick.getCards();
        // could replace with remove blind
        List<Card> playerCards = this.getHand().getCards();
        ListIterator<Card> iterator = playerCards.listIterator();
        while(iterator.hasNext()){
            Card c = iterator.next();
            if(c.equals(cards.get(0)) || c.equals(cards.get(1))){
                iterator.remove();
            }
        }
        return this.getHand().getCards();
    }

    @Override
    public String toString() {
        return user.toString();
    }

    /**
     *
     * @param card
     * @param followSuitTrump
     * @param followSuit
     * @return
     */
    public Card playCard(Card card, boolean followSuitTrump, String followSuit) {

        if(followSuitTrump){
            if(hasTrump()) {
                if(card.isTrumpSuit()) {
                    if (cardPlayed(card)) return card;
                }
                return null;
            } else {
                if (cardPlayed(card)) return card;
            }
        } else {
            if(hasFailOfSuit(followSuit)) {
                if(card.getSuit().equals(followSuit)) {
                    if(cardPlayed(card)) return card;
                }
                return null;
            } else {
                if(cardPlayed(card)) return card;
            }
        }
        // if bad
        return null;
    }

    private boolean cardPlayed(Card card) {
        for (int i = 0; i < hand.getCards().size(); i++) {
            if (hand.getCards().get(i).equals(card)) {
                // check followSuitTrump
                hand.getCards().remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean hasFailOfSuit(String failSuit) {
        for(Card c : hand.getCards()) {
            if(!c.isTrumpSuit() && c.getSuit().equals(failSuit)) {
                return true;
            }
        }
        return false;
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

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return
     */
    public boolean hasCards() {
        return !hand.getCards().isEmpty();
    }

    /**
     *
     * @param suit
     * @param value
     * @return
     */
    public Card getCard(String suit, String value) {
        for (Card c: hand.getCards()) {
            if(c.equals(new Card(suit,value))) return c;
        }
        throw new IllegalStateException("Card not found in players hand");
    }
}

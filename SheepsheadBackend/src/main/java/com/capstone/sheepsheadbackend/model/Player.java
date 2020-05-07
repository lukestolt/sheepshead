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
     * Create a new player for the given user
     * @param user User to create player for
     */
    public Player(User user) {
        this.user = user;
        tricks = new ArrayList<>();
        hand = new Hand();
    }

    /**
     * Get user associated with the player
     * @return User associated with the player
     */
    public User getUser() {
        return user;
    }

    /**
     * Get The hand of the player
     * @return Hand of the player
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Get the number of tricks played so far
     * @return Number of tricks played so far
     */
    public int getNumTricks(){return this.tricks.size();};

    /**
     * Set hand to given hand
     * @param hand Hand to use to set
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Give blind to this player
     * @param cards Cards to give to player
     */
    public void giveBlind(List<Card> cards){
        this.getHand().addCard(cards.get(0));
        this.getHand().addCard(cards.get(1));
    }

    /**
     * Remove Blind from player
     * @param blind blind to remove
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
     * Indicate that this player won the trick
     * @param trick Trick that player won
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
     * Play given card and verify that it follows suit
     * @param card Card to play
     * @param followSuitTrump Boolean of if follow suit is trump
     * @param followSuit Actual suit of the follow suit card
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
     * Get the players score
     * @return score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Check if player has cards in hand
     * @return Whether the player has cards in their hand
     */
    public boolean hasCards() {
        return !hand.getCards().isEmpty();
    }

    /**
     * Get given card from players hand
     * @param suit Suit of card to get
     * @param value Value of card to get
     * @return Card from players hand or null if not there
     */
    public Card getCard(String suit, String value) {
        for (Card c: hand.getCards()) {
            if(c.equals(new Card(suit,value))) return c;
        }
        throw new IllegalStateException("Card not found in players hand");
    }
}

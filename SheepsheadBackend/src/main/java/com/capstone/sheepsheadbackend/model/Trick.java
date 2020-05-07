package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.Pair;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trick {

    private int score;
    private List<Pair<Card, Player>> cards;
    private final int numPlayers;

    /**
     * Create new Trick with given number of players
     * @param numPlayers number of players in the game
     */
    public Trick(int numPlayers) {
        this.numPlayers = numPlayers;
        cards = new ArrayList<>(numPlayers);
    }

    /**
     * Add a card to the trick
     * @param c Card to add to the trick
     * @param p Player that played the card
     */
    public void addCard(Card c, Player p) {
        if(cards.size() < numPlayers) {
            cards.add(new Pair<>(c,p));
            score += SheepsheadCardValue.fromStrValue(c.getValue()).getPointValue().getPointValue();
        }
    }

    /**
     * Get the winner of the trick
     * @return Player who played to highest valued card
     */
    public Player getWinner() {
        Pair<Card, Player> max = cards.get(0);
        for(int i = 1; i < cards.size(); i++) {
            Pair<Card, Player> cp = cards.get(i);
            int winner = max.getK().compareTo(cp.getK());
            if(winner > 0) {
                max = cp;
            }
        }
        return max.getV();
    }

    /**
     * Get the score total for the trick
     * @return Total score of the trick
     */
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cnt = 0;
        str.append("Trick: ");
        for(Pair<Card, Player> c : cards) {
            str.append(cnt).append(":").append(c.getK().toString());
            cnt++;
        }
        return str.toString();
    }

    /**
     * Check if trick is empty
     * @return Boolean value of whether the trick is empty or not
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Get the cards in the trick
     * @return Cards in the trick
     */
    public List<Card> getCards() {
        return cards.stream().map(Pair::getK).collect(Collectors.toList());
    }
}

package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.Pair;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trick {
    public int getScore() {
        return score;
    }

    private int score;
    private List<Pair<Card, Player>> cards;
    private final int numPlayers;

    public Trick(int numPlayers) {
        this.numPlayers = numPlayers;
        cards = new ArrayList<>(numPlayers);
    }

    public void addCard(Card c, Player p) {
        if(cards.size() < numPlayers) {
            cards.add(new Pair<>(c,p));
            score += SheepsheadCardValue.fromStrValue(c.getValue()).getPointValue().getPointValue();
        }
    }

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

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return cards.stream().map(Pair::getK).collect(Collectors.toList());
    }
}

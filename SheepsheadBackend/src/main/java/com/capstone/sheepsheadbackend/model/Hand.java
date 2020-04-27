package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;
    private int MAX_CAP;

    public Hand(int numPlayers) {
        MAX_CAP = SheepsheadDeck.DECKSIZE/numPlayers;
        hand = new ArrayList<>(MAX_CAP);
    }

    public Hand(){
        hand = new ArrayList<>();
    }

    public List<Card> getCards() {
        return hand;
    }

    public void addCard(Card c) {
        hand.add(c);
    }

//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        int cnt = 0;
//        for(Card c: hand) {
//            if(c == null){
//                stringBuilder.append(cnt).append(":").append(" ");
//                cnt++;
//                continue;
//            }
//            stringBuilder.append(cnt).append(":").append(c.toString()).append(" ");
//            cnt++;
//        }
//        return stringBuilder.toString();
//    }

}

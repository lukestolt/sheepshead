package com.capstone.sheepsheadbackend.model;


import com.capstone.sheepsheadbackend.util.CardSuits;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

import java.util.Random;

public class Deck {

    private Card[] deck;

    public Deck() {
        deck = new Card[52];
        initDeck(deck, SheepsheadCardValue.values(), CardSuits.values());
    }

    public static void initDeck(Card[] deck, SheepsheadCardValue[] values, CardSuits[] suits) {
        int cnt = 0;
        for (CardSuits suit : suits) {
            for (SheepsheadCardValue value : values) {
                deck[cnt] = Card.builder()
                        .suit(suit)
                        .val(value).build();
                cnt++;
            }
        }
    }

    public Card[] getDeck() {
        return deck;
    }

//    @Override
//    public String toString() {
//        StringBuilder str = new StringBuilder();
//        for(Card c : deck) {
//            str.append(c.toString()).append(", ");
//        }
//        return str.toString();
//    }

    /**
     * Shuffles a deck of cards
     * @param d Deck of cards to shuffle
     */
    public static void shuffle(Card[] d) {
        Random rand = new Random();

        for(int i = 0; i < 256; i++) {
            int r1 = rand.nextInt(d.length);
            int r2 = rand.nextInt(d.length);

            Card temp = d[r1];
            d[r1] = d[r2];
            d[r2] = temp;
        }
    }
}

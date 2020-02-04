package com.capstone.sheepsheadbackend.model;


import java.util.Random;

public class Deck {

    private static final String[] VALUES = new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private static final String[] SUITS = new String[]{"Hearts","Clubs","Diamonds","Spades"};

    private Card[] deck;

    public Deck() {
        deck = new Card[52];
        initDeck(deck, VALUES, SUITS);
    }

    public static void initDeck(Card[] deck, String[] values, String[] suits) {
        int cnt = 0;
        for(int i = 0; i < suits.length; ++i) {
            for(int j = 0; j < values.length; ++j) {
                deck[cnt] = Card.builder()
                            .suit(suits[i])
                            .val(values[j]).build();

                cnt++;
            }
        }
    }

    public Card[] getDeck() {
        return deck;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Card c : deck) {
            str.append(c.toString()).append(", ");
        }
        return str.toString();
    }

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

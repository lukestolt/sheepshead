package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.CardSuit;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SheepsheadDeck {
    public static final int DECKSIZE = 32;
    private static SheepsheadDeck instance = new SheepsheadDeck();

    private SheepsheadDeck(){}

    public SheepsheadDeck getInstance() {
        return instance;
    }

    public static void deal(Game g) {
        // TODO: remove this line to make the deck have the original size
        List<Card> deck = initDeck().subList(0,8);
//        List<Card> deck = initDeck();
        shuffle(deck);
//        deck = deck.subList(0,8);
        List<Card> newDeck = dealBlind(deck,g);
        dealHand(newDeck, g.getPlayers());
    }

    private static List<Card> dealBlind(List<Card>deck, Game g) {
        List<Card> blind = new ArrayList<Card>();
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            int randPos = rand.nextInt(deck.size());
            blind.add(deck.get(randPos));
            deck.remove(randPos);
        }
        g.setBlind(blind);
        return deck;
    }

    private static void dealHand(List<Card> deck, List<Player> players) {
        Random rand = new Random();
        int deckSize = deck.size();
        players.forEach(player -> {
            for (int i = 0; i < deckSize/players.size(); i++) {
                int randPos = rand.nextInt(deck.size());
                player.getHand().addCard(deck.get(randPos));
                deck.remove(randPos);
            }
        });
    }

    private static List<Card> initDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (SheepsheadCardValue value : SheepsheadCardValue.values()) {
                cards.add(new Card(suit.getStrSuit(), value.getStrValue()));
            }
        }
        return cards;
    }

    /**
     * Shuffles a deck of cards
     * @param d Deck of cards to shuffle
     */
    private static void shuffle(List<Card> d) {
        Random rand = new Random();

        for(int i = 0; i < 256; i++) {
            int r1 = rand.nextInt(d.size());
            int r2 = rand.nextInt(d.size());

            Card temp = d.get(r1);
            d.set(r1, d.get(r2));
            d.set(r2, temp);
        }
    }
}

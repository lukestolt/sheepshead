package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.CardSuits;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SheepsheadDeck {

    private List<Card> deck;

    public SheepsheadDeck() {
        deck = new ArrayList<>(32);
        Deck.initDeck(deck, SheepsheadCardValue.values(), CardSuits.values());
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void shuffle() {
        Deck.shuffle(deck);
    }

    public void deal(Game game) {
        List<Player> players = game.getPlayers();
        players.forEach(this::dealHand);
    }

    public void dealHand(Player player) {
        Random rand = new Random();
        Hand hand = new Hand();
        Card[] cards = new Card[6];
        for (int i = 0; i < 6; i++) {
            int randPos = rand.nextInt(deck.size());

            while(deck.get(randPos) == null) {
                randPos = rand.nextInt(deck.size());
            }
            cards[i] = deck.get(randPos);
            deck.remove(randPos);
        }
        hand.setHand(cards);
        player.setHand(hand);
    }
}

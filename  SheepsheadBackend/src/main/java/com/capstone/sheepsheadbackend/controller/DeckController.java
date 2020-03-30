package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeckController {
    SheepsheadDeck deck = new SheepsheadDeck();

    @GetMapping(value = "/getDeck")
    public Deck getDeck() {
        return new Deck();
    }

    @GetMapping(value = "/getShuffledDeck")
    public Deck getShuffledDeck() {
        Deck deck = new Deck();
        Deck.shuffle(deck.getDeck());
        return deck;
    }

    @GetMapping(value = "/getSheepsheadDeck")
    public SheepsheadDeck getSheepsheadDeck() {
        return new SheepsheadDeck();
    }

    @GetMapping(value = "/getShuffledSheepsheadDeck")
    public SheepsheadDeck getShuffledSheepsheadDeck() {
        SheepsheadDeck deck = new SheepsheadDeck();
        Deck.shuffle(deck.getDeck());
        return deck;
    }

    @GetMapping(value = "/getRandomDeckCard")
    public Card getRandomDeckCard() {
        SheepsheadDeck deck = new SheepsheadDeck();
        Deck.shuffle(deck.getDeck());
        return deck.getDeck().get(0);
    }

    @PostMapping(value = "/isTrumpSuit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Card isTrumpSuit(@RequestBody Card jsonCard) {
        System.out.println(Card.isTrumpSuit(jsonCard));
        return jsonCard;
    }

    @GetMapping(value = "/getHand")
    public Hand getHand() {
        Deck.shuffle(deck.getDeck());
        Player p = new Player(null);
        deck.dealHand(p,5);
        return p.getHand();
    }
}

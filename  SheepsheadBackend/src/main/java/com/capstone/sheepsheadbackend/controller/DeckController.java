package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.Card;
import com.capstone.sheepsheadbackend.model.Deck;
import com.capstone.sheepsheadbackend.model.SheepsheadDeck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckController {

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
}

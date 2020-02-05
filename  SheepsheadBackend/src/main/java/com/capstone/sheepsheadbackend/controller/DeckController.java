package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.Card;
import com.capstone.sheepsheadbackend.model.Deck;
import com.capstone.sheepsheadbackend.model.SheepsheadDeck;
import com.capstone.sheepsheadbackend.util.CardStrength;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/getRandomDeckCard")
    public Card getRandomDeckCard() {
        SheepsheadDeck deck = new SheepsheadDeck();
        Deck.shuffle(deck.getDeck());
        return deck.getDeck()[0];
    }

    @PostMapping(value = "/isTrumpSuit", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Card isTrumpSuit(@RequestBody Card jsonCard) {
        System.out.println(jsonCard);
        System.out.println(jsonCard.getSuit().getCardStrength());
        System.out.println(jsonCard.getVal().getCardStrength());
        if(jsonCard.getSuit().getCardStrength() ==
                CardStrength.TRUMPSUIT ||
                jsonCard.getVal().getCardStrength() ==
                        CardStrength.TRUMPSUIT) {
            System.out.println("True");;
        } else {
            System.out.println("false");
        }
        return jsonCard;
    }

    public class StringResponse {

        private String response;

        public StringResponse(String s) {
            this.response = s;
        }

        // get/set omitted...
    }

}

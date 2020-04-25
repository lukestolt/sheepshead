package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Trick {
    private List<Card> cards;

    public Trick() {
        cards = new ArrayList<>();
    }

    public void addTrick(List<Card> playedCards) {
        cards.addAll(playedCards);
    }
}

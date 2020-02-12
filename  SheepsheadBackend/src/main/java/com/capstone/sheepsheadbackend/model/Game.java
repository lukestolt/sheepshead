package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private String uuid;
    private List<Player> players;
    private SheepsheadDeck deck;
    private Player dealer;

    public Game() {
        uuid = UUID.randomUUID().toString();
        players = new ArrayList<>();
        deck = new SheepsheadDeck();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    private void start() {
        deck.deal(this);
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getUID() {
        return uuid;
    }

    public <T> Comparable getCreated() {
        return null;
    }
}

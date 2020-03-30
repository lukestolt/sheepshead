package com.capstone.sheepsheadbackend.model;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

    private final String uuid;
    private List<Player> players;
    private SheepsheadDeck deck;
    private List<Card> blind;
    private Player dealer;
    private Player picker;
    private ServerSocket gamesSocket;
    private final int MAX_PLAYERS;
    private static boolean start;

    public Game(int numPlayers) {
        uuid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        deck = new SheepsheadDeck();
    }

    public boolean addPlayer(Player player) {

        if(players.size() >= MAX_PLAYERS) {
            return false;
        } else {
            players.add(player);
            return true;
        }
    }

    public void start() {
        if(!start) {
            start = true;
        }
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

    @Override
    public String toString(){
        return players.toString();
    }

    public void dealHands() {
        blind = deck.deal(this);
    }

    public void initDealer() {
        dealer = players.get(0);
    }

    public Player getPicker() {
        //TODO need to figure out how to assign picker
//        for(Player p: players) {
//
//        }
        picker = players.get(0);
        return picker;
    }
}

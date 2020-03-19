package com.capstone.sheepsheadbackend.model;

import java.util.HashMap;
import java.util.Map;

public class GamesManager {

    private Map<String, Game> games = new HashMap<>();
    private Game waitGame;

    public GamesManager() {

    }

    public void addPlayer(Player p) {
        // no game instance waiting
        if(waitGame == null) {
            waitGame = new Game(5);
            games.put(waitGame.getUID(), waitGame);
            waitGame.addPlayer(p);
        } else {
            // game instance waiting
            if(!waitGame.addPlayer(p)) {
                // initiate game and create new one
                waitGame = new Game(5);
                games.put(waitGame.getUID(), waitGame);
                waitGame.addPlayer(p);
            }
        }
    }


    public Game getWait() {
        return waitGame;
    }

    public int getGames() {
        return games.size();
    }
}

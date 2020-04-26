package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.controller.game.AbstractResponse;
import com.capstone.sheepsheadbackend.model.actions.Action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GamesManager {

    private Map<String, Game> games = new ConcurrentHashMap<>();
    private Game waitGame;

    public GamesManager() {

    }


    //TODO return GAME UUID & Player ID
    public void addPlayer(Player p) {
        // no game instance waiting
        if(waitGame == null) {
            waitGame = new Game(5);
            games.put(waitGame.getUGID(), waitGame);
            waitGame.addPlayer(p);
        } else {
            // game instance waiting
            if(!waitGame.addPlayer(p)) {
                // initiate game and create new one
                waitGame = new Game(5);
                games.put(waitGame.getUGID(), waitGame);
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

    public AbstractResponse addAction(Action action) {
        Game g = games.get(action.getGameId());
        return g.performAction(action);
    }
}

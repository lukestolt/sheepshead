package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.controller.game.AbstractResponse;
import com.capstone.sheepsheadbackend.controller.game.InitGameData;
import com.capstone.sheepsheadbackend.model.actions.Action;
import com.google.gson.Gson;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GamesManager {
    private static GamesManager gm;

    private Map<String, Game> games = new ConcurrentHashMap<>();
    private Game waitGame;
    private int numPlayers = 1;
    private Gson gson = new Gson();

    private SimpMessagingTemplate messageSender;

    private GamesManager() {
        waitGame = new Game(numPlayers);
    }

    public static GamesManager getInstance() {
        if (gm == null) {
            gm = new GamesManager();
        }
        return gm;
    }


    /**
     * @return - the String of the game
     * @param playerId - String of the id of the player
     */
    public String addPlayer(String playerId) {
        Player p = new Player(new User(playerId));
        synchronized (waitGame){
            // game instance waiting
            if(!waitGame.addPlayer(p)) {
                // initiate game and create new one
                waitGame = new Game(numPlayers);
                waitGame.addPlayer(p);
            }
            if(waitGame.isGameFull()) {
                waitGame.startGame();
                broadcastGameStatus(waitGame.getUGID());
                games.putIfAbsent(waitGame.getUGID(), waitGame);
            }
        }
        return waitGame.getUGID();
    }

    /***
     * sends a message if ready or not ready
     * @param gId
     */
    public void broadcastGameStatus(String gId) {
        String message = "not ready";
        Game g = this.games.get(gId);
        // it is looking for the wait game status then it isnt ready
        if(g != null && g.isGameReady()){
            message = "ready";
        }
        System.out.println(message);
        this.messageSender.convertAndSend("/topic/gamestatus/" + waitGame.getUGID(), this.gson.toJson(message));
    }

    /**
     *
     * @param gameId
     * @param playerId
     * @return players hand, and whose turn it is
     */
    public InitGameData getGameStartupData(String gameId, String playerId){
        Game g = games.get(gameId);
        List<Card> cards = g.getPlayerHand(playerId);
        InitGameData igd = new InitGameData(playerId,cards);
        return  igd;
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

    public void setMessageSender(SimpMessagingTemplate ms){
        this.messageSender = ms;
    }
}

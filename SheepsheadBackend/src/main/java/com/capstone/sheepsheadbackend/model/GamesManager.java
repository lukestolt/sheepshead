package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.model.response.AbstractResponse;
import com.capstone.sheepsheadbackend.model.actions.Action;
import com.capstone.sheepsheadbackend.model.response.GameInitResponse;
import com.capstone.sheepsheadbackend.model.response.WinningGameResponse;
import com.google.gson.Gson;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GamesManager {
    private static GamesManager gm;

    private Map<String, Game> games = new ConcurrentHashMap<>();
    private Game waitGame;
    private int numPlayers = 3;
    private Gson gson = new Gson();

    private SimpMessagingTemplate messageSender;

    private GamesManager() {
        waitGame = new Game(numPlayers);
    }

    /**
     * Get the instance of the Games Manager
     * @return Instance of the Games Manager
     */
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
    public String addPlayer(String playerId, String username) {
        Player p = new Player(new User(playerId, username));
        synchronized (waitGame){
            waitGame.addPlayer(p);
            String id = waitGame.getUGID();
            if(waitGame.isGameFull()){
                this.games.put(waitGame.getUGID(), waitGame);
                waitGame = new Game(numPlayers);
            }
            return id;
        }

    }

    /***
     * sends a message with game info if ready
     * also sends the blind to the player whose turn it is
     * @param gId
     */
    public void broadcastInitGameInfo(String gId) {
        Game g = this.games.get(gId);
        if(g != null){
            synchronized (g){
                g.startGame();
                List<Player> players =g.getPlayers();
                for(int x = 0; x < players.size(); x++){
                    String playerId = players.get(x).getUser().getUuid();
                    List<Player> opps = this.getOpponents(g, playerId);
                    List<String> oppIds = new ArrayList<>();
                    List<String> names = new ArrayList<>();
                    opps.forEach(opp -> {
                        names.add(opp.getUser().getUsername());
                    });
                    opps.forEach(opp -> {
                        oppIds.add(opp.getUser().getUuid());
                    });
                    GameInitResponse gir = new GameInitResponse(playerId,g.getUGID(),
                            g.getPlayerHand(playerId), names, oppIds, g.getCurrentPlayer().getUser().getUuid());

                    messageSender.convertAndSend("/topic/gameInit/" + playerId, gir);
                }
            }
        }
    }

    /**
     * Get the List of opponents in the game
     * @param g Game to get players from
     * @param playerId Player Id of requesting player
     * @return List of opponents
     */
    public List<Player> getOpponents(Game g, String playerId){
//        Game g = this.games.get(gameId);
        // find the player and remove it so it gets the opponents
        List<Player> opp = new ArrayList<Player>(g.getPlayers());
        int oppSize= opp.size();
        for (int i = 0; i < oppSize; i++) {
            if (opp.get(i).getUser().getUuid().equals(playerId)) {
                opp.remove(i);
                break;
            }
        }
        return opp;
    }

    /**
     * Get the game that is in the waiting status
     * @return Waiting Game
     */
    public Game getWait() {
        return waitGame;
    }

    /**
     * Get the number of active games
     * @return number of active games
     */
    public int getGames() {
        return games.size();
    }

    /**
     * Add an action to the games manager and perform the action
     * @param action Action to add and perform
     * @return Response to given action
     */
    // annotate this so know what this returns given an action
    public AbstractResponse addAction(Action action) {
        Game g = games.get(action.getGameId());
        synchronized (g) {
            AbstractResponse a = g.performAction(action);
            // broadcast the trick information
            messageSender.convertAndSend("/topic/trickResponse/" + g.getUGID(), this.gson.toJson(g.getGameTrickData()));
            if(a instanceof WinningGameResponse){
                this.games.remove(g.getUGID());
            }
            return a;
        }
    }

    /**
     * Set the message Sender for the games manager
     * @param ms Message Sender to set
     */
    public void setMessageSender(SimpMessagingTemplate ms){
        this.messageSender = ms;
    }
}

package com.capstone.sheepsheadbackend.model.game;

public class FindGameRequest {

    public String playerId;
    public String username;
    public String[] numPlayerOptions;

    /**
     * Create new request to find a game
     * @param pId Player ID
     * @param username Player Username
     * @param npo Number of players
     */
    FindGameRequest(String pId, String username, String[] npo) {
        this.playerId = pId;
        this.username = username;
        numPlayerOptions = npo;
    }

    /**
     * Create new request to find a game. No Parameters.
     */
    FindGameRequest(){
    }

    /**
     * Set players ID to given ID
     * @param playerId ID to assign
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Set the number of players when looking for a game
     * @param numPlayerOptions number of players in game
     */
    public void setNumPlayerOptions(String[] numPlayerOptions) {
        this.numPlayerOptions = numPlayerOptions;
    }
}

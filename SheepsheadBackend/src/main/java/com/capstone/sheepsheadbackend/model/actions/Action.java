package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;

public abstract class Action {
    private String action;
    private String gameId;
    private String playerId;

    /**
     * Create a new action
     */
    public Action() {}

    /**
     *
     * @param action
     * @param playerId
     * @param gameId
     */
    public Action(String action, String playerId, String gameId) {
        this.action = action;
        this.playerId = playerId;
        this.gameId = gameId;
    }

    /**
     * Set action parameter
     * @param action action parameter
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Set the game Id of the action
     * @param gameId Game Id of the action
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * Set the player Id of the action
     * @param playerId Player Id of the action
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Perform this action using the GamesManager
     * @param gm GamesManager to perform action on
     */
    public abstract void perform(GamesManager gm);

    /**
     * Get the action
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Get the Game Id
     * @return Game Id
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Get the Player Id
     * @return Player Id
     */
    public String getPlayerId() {
        return playerId;
    }
}

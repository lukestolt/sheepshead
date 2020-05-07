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
     * Set action parameter
     * @param action action parameter
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     *
     * @param gameId
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     *
     * @param playerId
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

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
     *
     * @param gm
     */
    public abstract void perform(GamesManager gm);

    /**
     *
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @return
     */
    public String getGameId() {
        return gameId;
    }

    /**
     *
     * @return
     */
    public String getPlayerId() {
        return playerId;
    }
}

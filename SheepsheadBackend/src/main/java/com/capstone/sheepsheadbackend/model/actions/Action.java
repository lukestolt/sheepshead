package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;

public abstract class Action {
    private String action;
    private String gameId;
    private String playerId;

    public Action() {}

    public void setAction(String action) {
        this.action = action;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Action(String action, String playerId, String gameId) {
        this.action = action;
        this.playerId = playerId;
        this.gameId = gameId;
    }

    public abstract void perform(GamesManager gm);

    public String getAction() {
        return action;
    }

    public String getGameId() {
        return gameId;
    }

    public String getPlayerId() {
        return playerId;
    }
}

package com.capstone.sheepsheadbackend.model.actions;

import com.capstone.sheepsheadbackend.model.GamesManager;

public abstract class Action {
    private String action;
    private String gameId;
    private String playerId;
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

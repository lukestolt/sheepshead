package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Player;
import com.google.gson.Gson;

import java.util.List;

public class WinningGameResponse extends AbstractResponse {
    Player winner;
    List<Integer> playerPoints;
    List<String> playerIds;

    public WinningGameResponse(String playerId, String gameId, Player winner, List<Integer> playerPoints, List<String> playerIds) {
        super(playerId, gameId, "winGame");
        this.winner = winner;
        this.playerPoints = playerPoints;
        this.playerIds = playerIds;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}

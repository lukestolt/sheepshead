package com.capstone.sheepsheadbackend.controller.game;

import com.capstone.sheepsheadbackend.model.Player;

import java.util.List;

public class WinningGameResponse extends AbstractResponse {
    Player winner;
    List<Integer> playerPoints;
    List<String> playerIds;

    public WinningGameResponse(String playerId, String gameId, String responseType, Player winner, List<Integer> playerPoints, List<String> playerIds) {
        super(playerId, gameId, responseType);
        this.winner = winner;
        this.playerPoints = playerPoints;
        this.playerIds = playerIds;
    }

    @Override
    public String createResponse() {
        return null;
    }
}

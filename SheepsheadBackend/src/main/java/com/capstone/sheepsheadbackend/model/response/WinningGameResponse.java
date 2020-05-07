package com.capstone.sheepsheadbackend.model.response;

import com.google.gson.Gson;

import java.util.List;

public class WinningGameResponse extends AbstractResponse {
    public String winnerName;
    public List<Integer> playerPoints;
    public List<String> playerNames;

    /**
     * Create Response for winning the game
     * @param playerId Player Id of response
     * @param gameId Game Id of response
     * @param winnerName Username of the Winner
     * @param playerPoints Points of all players in game
     * @param playerNames Usernames of all players
     */
    public WinningGameResponse(String playerId, String gameId, String winnerName, List<Integer> playerPoints, List<String> playerNames) {
        super(playerId, gameId, "winGame");
        this.winnerName = winnerName;
        this.playerPoints = playerPoints;
        this.playerNames = playerNames;
    }

    @Override
    public String createResponse() {
        String s = new Gson().toJson(this);
        return s;
    }
}

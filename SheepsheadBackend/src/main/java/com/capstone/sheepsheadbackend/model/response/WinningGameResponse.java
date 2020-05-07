package com.capstone.sheepsheadbackend.model.response;

import com.google.gson.Gson;

import java.util.List;

public class WinningGameResponse extends AbstractResponse {
    public String winnerName;
    public List<Integer> playerPoints;
    public List<String> playerNames;

    /**
     *
     * @param playerId
     * @param gameId
     * @param winnerName
     * @param playerPoints
     * @param playerNames
     */
    public WinningGameResponse(String playerId, String gameId, String winnerName, List<Integer> playerPoints, List<String> playerNames) {
        super(playerId, gameId, "winGame");
        this.winnerName = winnerName;
        this.playerPoints = playerPoints;
        this.playerNames = playerNames;
    }

    /**
     *
     * @return
     */
    public String createResponse() {
        String s = new Gson().toJson(this);
        return s;
    }
}

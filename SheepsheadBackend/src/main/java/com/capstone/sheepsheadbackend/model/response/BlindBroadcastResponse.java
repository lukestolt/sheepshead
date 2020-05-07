package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class BlindBroadcastResponse extends AbstractResponse{
    String nextTurnId;

    /**
     * Create new response to broadcast the blind
     * @param playerId Player Id for response
     * @param gameId Game Id for response
     * @param nextTurnId Player Id for next turn
     */
    public BlindBroadcastResponse(String playerId, String gameId, String nextTurnId){
        super(playerId, gameId, "blindAction");
        this.nextTurnId = nextTurnId;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}


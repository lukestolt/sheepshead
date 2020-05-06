package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class BlindBroadcastResponse extends AbstractResponse{
    String nextTurnId;

    public BlindBroadcastResponse(String playerId, String gameId, String nextTurnId){
        super(playerId, gameId, "blindAction");
        this.nextTurnId = nextTurnId;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}


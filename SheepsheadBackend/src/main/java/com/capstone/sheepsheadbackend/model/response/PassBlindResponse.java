package com.capstone.sheepsheadbackend.model.response;

import com.google.gson.Gson;

public class PassBlindResponse extends AbstractResponse {
    String nextTurnId;

    public PassBlindResponse(String playerId, String gameId, String nextTurnId){
        super(playerId, gameId, "passBlind");
        this.nextTurnId = nextTurnId;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}

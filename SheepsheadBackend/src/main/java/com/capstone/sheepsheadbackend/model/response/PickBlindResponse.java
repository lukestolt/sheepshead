package com.capstone.sheepsheadbackend.model.response;

import com.google.gson.Gson;

public class PickBlindResponse extends AbstractResponse {
    String nextTurnId;

    public PickBlindResponse(String playerId, String gameId, String nextTurnId){
        super(playerId, gameId, "pickBlind");
        this.nextTurnId = nextTurnId;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}

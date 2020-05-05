package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class AcceptBlindResponse extends AbstractResponse{

    public AcceptBlindResponse(String playerId, String gameId){
        super(playerId, gameId, "blindAccepted");
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}

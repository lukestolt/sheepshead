package com.capstone.sheepsheadbackend.controller.game;

import com.google.gson.Gson;

public class ErrorResponse extends AbstractResponse {
    private String message;

    public ErrorResponse(String playerId, String gameId, String responseType, String message) {
        super(playerId, gameId, responseType);
        this.message = message;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}

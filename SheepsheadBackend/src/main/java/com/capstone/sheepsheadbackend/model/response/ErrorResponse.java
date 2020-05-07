package com.capstone.sheepsheadbackend.model.response;

import com.google.gson.Gson;

public class ErrorResponse extends AbstractResponse {
    private String message;

    /**
     *
     * @param playerId
     * @param gameId
     * @param message
     */
    public ErrorResponse(String playerId, String gameId, String message) {
        super(playerId, gameId, "ERROR");
        this.message = message;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}

package com.capstone.sheepsheadbackend.model.response;

import com.google.gson.Gson;

public class ErrorResponse extends AbstractResponse {
    private String message;

    /**
     * Create a new Response when an action returns an error
     * @param playerId Player Id of response
     * @param gameId Game Id of response
     * @param message Error Message
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

package com.capstone.sheepsheadbackend.model.response;


public abstract class AbstractResponse {
    public String playerId;
    public String gameId;
    public String responseType;
    public abstract String createResponse();

    public AbstractResponse(String playerId, String gameId, String responseType){
        this.playerId = playerId;
        this.gameId = gameId;
        this.responseType = responseType;
    }
}

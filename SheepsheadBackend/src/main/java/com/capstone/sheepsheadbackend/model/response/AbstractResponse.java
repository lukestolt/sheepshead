package com.capstone.sheepsheadbackend.model.response;


public abstract class AbstractResponse {
    public String playerId;
    public String gameId;
    public String responseType;

    /**
     *
     * @param playerId
     * @param gameId
     * @param responseType
     */
    public AbstractResponse(String playerId, String gameId, String responseType){
        this.playerId = playerId;
        this.gameId = gameId;
        this.responseType = responseType;
    }

    /**
     *
     * @return
     */
    public abstract String createResponse();
}

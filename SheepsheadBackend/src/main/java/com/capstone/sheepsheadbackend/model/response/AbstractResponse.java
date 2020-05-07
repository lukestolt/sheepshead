package com.capstone.sheepsheadbackend.model.response;


public abstract class AbstractResponse {
    public String playerId;
    public String gameId;
    public String responseType;

    /**
     * Generic Response to Actions
     * @param playerId Player Id for response
     * @param gameId Game Id for response
     * @param responseType Type of response
     */
    public AbstractResponse(String playerId, String gameId, String responseType){
        this.playerId = playerId;
        this.gameId = gameId;
        this.responseType = responseType;
    }

    /**
     * Return Response as String
     * @return String version of response
     */
    public abstract String createResponse();
}

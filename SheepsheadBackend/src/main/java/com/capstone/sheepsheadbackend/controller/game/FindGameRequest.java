package com.capstone.sheepsheadbackend.controller.game;

public class FindGameRequest {

    public String playerId;
    public String[] numPlayerOptions;

    FindGameRequest(String pId, String[] npo) {
        this.playerId = pId;
        numPlayerOptions = npo;
    }
    FindGameRequest(){

    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setNumPlayerOptions(String[] numPlayerOptions) {
        this.numPlayerOptions = numPlayerOptions;
    }
}

package com.capstone.sheepsheadbackend.controller.game;

public class FindGameRequest {

    public String playerId;
    public String username;
    public String[] numPlayerOptions;

    FindGameRequest(String pId, String username, String[] npo) {
        this.playerId = pId;
        this.username = username;
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

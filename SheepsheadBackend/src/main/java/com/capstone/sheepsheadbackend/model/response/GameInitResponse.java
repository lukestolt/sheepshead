package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class GameInitResponse extends AbstractResponse {
    private List<Card> cards;
    private List<String> oppNames;
    private List<String> oppIds;
    private String startingPlayer;

    public GameInitResponse(String playerId, String gameId, List<Card> cards, List<String> oppNames, List<String> oppIds, String startingPlayer) {
        super(playerId, gameId, "gameInit");
        this.cards = cards;
        this.oppNames = oppNames;
        this.oppIds = oppIds;
        this.startingPlayer = startingPlayer;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<String> getOppNames() {
        return oppNames;
    }

    public void setOppNames(List<String> oppNames) {
        this.oppNames = oppNames;
    }

    public List<String> getOppIds() {
        return oppIds;
    }

    public void setOppIds(List<String> oppIds) {
        this.oppIds = oppIds;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }

    public String getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(String startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}
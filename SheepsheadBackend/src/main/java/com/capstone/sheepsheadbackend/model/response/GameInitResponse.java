package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class GameInitResponse extends AbstractResponse {
    private List<Card> cards;
    private List<String> oppNames;
    private List<String> oppIds;
    private String startingPlayer;

    /**
     *
     * @param playerId
     * @param gameId
     * @param cards
     * @param oppNames
     * @param oppIds
     * @param startingPlayer
     */
    public GameInitResponse(String playerId, String gameId, List<Card> cards, List<String> oppNames, List<String> oppIds, String startingPlayer) {
        super(playerId, gameId, "gameInit");
        this.cards = cards;
        this.oppNames = oppNames;
        this.oppIds = oppIds;
        this.startingPlayer = startingPlayer;
    }

    /**
     *
     * @return
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     *
     * @param cards
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     *
     * @return
     */
    public List<String> getOppNames() {
        return oppNames;
    }

    /**
     *
     * @param oppNames
     */
    public void setOppNames(List<String> oppNames) {
        this.oppNames = oppNames;
    }

    /**
     *
     * @return
     */
    public List<String> getOppIds() {
        return oppIds;
    }

    /**
     *
     * @param oppIds
     */
    public void setOppIds(List<String> oppIds) {
        this.oppIds = oppIds;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }

    /**
     *
     * @return
     */
    public String getStartingPlayer() {
        return startingPlayer;
    }

    /**
     *
     * @param startingPlayer
     */
    public void setStartingPlayer(String startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}

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
     * Create new Response for Initiating the game
     * @param playerId Player Id for response
     * @param gameId Game Id for response
     * @param cards Cards of player hand
     * @param oppNames Usernames of the opponents
     * @param oppIds Player Id's of the opponents
     * @param startingPlayer Player Id of starting player
     */
    public GameInitResponse(String playerId, String gameId, List<Card> cards, List<String> oppNames, List<String> oppIds, String startingPlayer) {
        super(playerId, gameId, "gameInit");
        this.cards = cards;
        this.oppNames = oppNames;
        this.oppIds = oppIds;
        this.startingPlayer = startingPlayer;
    }

    /**
     * Get cards of player
     * @return Cards of player
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Set Cards of player to given Cards
     * @param cards Cards used to set
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Get names of opponents
     * @return Names of opponents
     */
    public List<String> getOppNames() {
        return oppNames;
    }

    /**
     * Set names of opponents with given names
     * @param oppNames Names of opponents to set
     */
    public void setOppNames(List<String> oppNames) {
        this.oppNames = oppNames;
    }

    /**
     * Get Player Id's of opponents
     * @return Player Id's of opponents
     */
    public List<String> getOppIds() {
        return oppIds;
    }

    /**
     * Set Player Id's of opponents with given Id's
     * @param oppIds Player Id's used to set
     */
    public void setOppIds(List<String> oppIds) {
        this.oppIds = oppIds;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }

    /**
     * Get Starting Player Id
     * @return Player Id of starting player
     */
    public String getStartingPlayer() {
        return startingPlayer;
    }

    /**
     * Set starting player Id to given Player Id
     * @param startingPlayer Player Id to set startingPlayer to
     */
    public void setStartingPlayer(String startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
}

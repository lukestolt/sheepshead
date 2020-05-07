package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.model.actions.BlindAction;
import com.capstone.sheepsheadbackend.model.response.*;
import com.capstone.sheepsheadbackend.model.actions.Action;
import com.capstone.sheepsheadbackend.model.actions.PlayCardAction;
import com.capstone.sheepsheadbackend.util.Pair;
import com.capstone.sheepsheadbackend.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private final String ugid;
    private List<Player> players;
    private List<Card> blind;
    private Player dealer;
    private Player picker;
    private List<Trick> tricks;
    private Trick currentTrick;
    private final int MAX_PLAYERS;
    private boolean start = false;
    private Player currentPlayer;

    private boolean followSuitTrump = false;
    private String followSuit = null;

    /**
     *
     * @param numPlayers
     */
    public Game(int numPlayers) {
        ugid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        tricks = new ArrayList<>();
    }

    /**
     *
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     *
     * @return
     */
    public boolean isGameFull(){
        return players.size() == MAX_PLAYERS;
    }

    /**
     *
     */
    public void startGame() {
        if(!start) {
            start = true;
            SheepsheadDeck.deal(this);
            initDealer();
            dealBlind();
        }
    }

    /**
     *
     */
    private void dealBlind(){
        List<Card> pickerCards = this.currentPlayer.getHand().getCards();
        // adds the blind to the end of the hand
        pickerCards.addAll(this.blind);
    }

    /**
     *
     * @return
     */
    public boolean isGameReady(){
        return this.start;
    }

    /**
     *
     * @param a
     * @return
     */
    public AbstractResponse performAction(Action a) {
        switch(a.getAction()) {
            case "PlayCard": {
                AbstractResponse response;
                PlayCardAction pca = (PlayCardAction) a;
                Player p = getPlayer(a.getPlayerId());
                Card c = p.getCard(pca.getSuit(), pca.getValue());
                if (currentTrick == null) {
                    currentTrick = new Trick(MAX_PLAYERS);
                    followSuitTrump = c.isTrumpSuit();
                    followSuit = c.getSuit();
                }
                Card ret = p.playCard(c, followSuitTrump, followSuit);
                if (ret == null) {
                    // bad player needs to resend
                    response = new ErrorResponse(a.getPlayerId(), a.getGameId(), "ERROR");
                } else {
                    // return new game state stuff
                    currentTrick.addCard(ret, p);
                    // testing
                    Pair<Player, Trick> updatedTrick = checkWonTrick();
                    ///
                    response = checkGameOver();
                    if (response == null) {
                        Player nextTurn = players.get(nextPlayer(nextPlayer(players.indexOf(p))));
                        currentPlayer = nextTurn;
                        String playerId = a.getPlayerId();
                        String gameId = a.getGameId();
                        List<Card> cards = p.getHand().getCards();
                        String uuid = nextTurn.getUser().getUuid();
                        List<Card> trickCards;
                        if (updatedTrick.getV() == null) {
                            trickCards = null;
                            uuid = updatedTrick.getK().getUser().getUuid();
                        } else {
                            trickCards = currentTrick.getCards();
                        }
                        response = new PlayCardResponse(playerId, gameId, cards, uuid, trickCards);
                    }
                }
                return response;
            }
            case "PickBlind": {
                BlindAction pba = (BlindAction) a;
                Player p = getPlayer(a.getPlayerId());
                this.picker = p;
                Trick blindTrick = new Trick(MAX_PLAYERS);
                blindTrick.addCard(pba.cards[0], p);
                blindTrick.addCard(pba.cards[1], p);
//                this.tricks.add(blindTrick);
                List<Card> cs = p.burryCards(blindTrick);
                Player nextTurn = players.get(nextPlayer(nextPlayer(players.indexOf(p))));
                currentPlayer = nextTurn;
                return new AcceptBlindResponse(pba.getPlayerId(), pba.getGameId(), p.getUser().getUsername());
            }
             // in the future can look into forcing player to take the blind
            case "PassBlind": {
                BlindAction pba = (BlindAction) a;
                Player p = getPlayer(a.getPlayerId());
                //remove the blind from the current player
                p.removeBlind(this.blind);
                Player nextTurn = players.get(nextPlayer(nextPlayer(players.indexOf(p))));
                // give the next person the blind
                nextTurn.giveBlind(this.blind);
                currentPlayer = nextTurn;
            }
            default:
                break;
        }
        return null;
    }

    /**
     *
     * @return
     */
    Pair<Player, Trick> checkWonTrick() {
        List<Card> currentTrickCards = currentTrick.getCards();
        Player p = null;
        if(currentTrickCards.size() == this.MAX_PLAYERS){
            tricks.add(currentTrick);
            p = currentTrick.getWinner();
            this.getPlayer(p.getUser().getUuid()).wonTrick(currentTrick);
            currentTrick = null;
        }
        return new Pair<Player, Trick>(p, currentTrick);
    }

    /**
     *
     * @return
     */
    public List<Pair<String,Integer>> getGameTrickData() {
        // get the size off the tricks each player has
        // return array of playerid and tricknumber
        List<Pair<String,Integer>> data = new ArrayList<Pair<String,Integer>>();
        this.players.forEach(p -> {
            data.add(new Pair(p.getUser().getUuid(), p.getNumTricks()));
        });
        return data;
    }

    /**
     *
     * @return AbstractResponse if the game if over or null if it isn't
     */
    AbstractResponse checkGameOver() {
        boolean noCards = true;
        for(int i = 0; i < players.size(); i++) {
            noCards = noCards && (players.get(i).getHand().getCards().isEmpty());
        }
        if(noCards) {
            Player p = getWinner();
            List<Integer> scores = players.stream().map(Player::getScore).collect(Collectors.toList());
            List<String> playerNames = players.stream().map(Player::getUser).map(User::getUsername).collect(Collectors.toList());
            return new WinningGameResponse(p.getUser().getUuid(), this.ugid, p.getUser().getUsername(), scores, playerNames);
        }
        return null;
    }

    private Player getWinner() {
        Player winner = players.get(0);
        for(int i = 1; i < players.size(); i++) {
            if(winner.getScore() < players.get(i).getScore()){
                winner = players.get(i);
            }
        }
        return winner;
    }

    private Player getPlayer(String playerId) {
        synchronized (players){
            for(Player p: players) {
                if(p.getUser().getUuid().equals(playerId)) return p;
            }
            // Player ID doesn't exist
            return null;
        }
    }

    /**
     *
     * @return
     */
    public Player getDealer() {
        return dealer;
    }

    /**
     *
     * @return
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    public String getUGID() {
        return ugid;
    }

    @Override
    public String toString(){
        return players.toString();
    }

    /**
     *
     */
    public void initDealer() {
        int x = Util.getRandomIntBound(MAX_PLAYERS);
        dealer = players.get(x);
        currentPlayer = players.get(nextPlayer(x));
    }

    /**
     *
     * @param index
     * @return
     */
    private int nextPlayer(int index) {
        if(index+1 < MAX_PLAYERS) {
            return index+1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param playerId
     * @return
     */
    public List<Card> getPlayerHand(String playerId){
            Player p = this.getPlayer(playerId);
            return p.getHand().getCards();
    }

    /**
     *
     * @param deck
     */
    public void setBlind(List<Card> deck) {
        this.blind = deck;
    }

    /**
     *
     * @return
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

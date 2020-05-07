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
     * Create new Game with given number of players
     * @param numPlayers Number of players
     */
    public Game(int numPlayers) {
        ugid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        tricks = new ArrayList<>();
    }

    /**
     * Add given player to the game
     * @param player Player to add to the game
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Check if the game is full
     * @return boolean of whether game is full or not
     */
    public boolean isGameFull(){
        return players.size() == MAX_PLAYERS;
    }

    /**
     * Start the game
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
     * Deal the blind
     */
    private void dealBlind(){
        List<Card> pickerCards = this.currentPlayer.getHand().getCards();
        // adds the blind to the end of the hand
        pickerCards.addAll(this.blind);
    }

    /**
     * Check if the game is ready
     * @return boolean of whether the game is ready or not
     */
    public boolean isGameReady(){
        return this.start;
    }

    /**
     * Perform the given action on this game
     * @param a Action to perform
     * @return Response to performing the action
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
     * Check if the trick has been won and return the player and the trick
     * @return Pair of trick winning player and trick
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
     * Get the data of the trick
     * @return List of players Id's and their number of tricks won
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
     * Check if the game has been won
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
     * Get the dealer of the game
     * @return Dealer of the game
     */
    public Player getDealer() {
        return dealer;
    }

    /**
     * Get the list of players in the game
     * @return List of players in the game
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the Game Id
     * @return Game Id
     */
    public String getUGID() {
        return ugid;
    }

    @Override
    public String toString(){
        return players.toString();
    }

    /**
     * Initialize the dealer to a random player
     */
    public void initDealer() {
        int x = Util.getRandomIntBound(MAX_PLAYERS);
        dealer = players.get(x);
        currentPlayer = players.get(nextPlayer(x));
    }

    /**
     * Get the index of the next player
     * @param index Index of current player
     * @return Index of next player
     */
    private int nextPlayer(int index) {
        if(index+1 < MAX_PLAYERS) {
            return index+1;
        } else {
            return 0;
        }
    }

    /**
     * Get the hand of the given player
     * @param playerId Player Id to get hand of
     * @return
     */
    public List<Card> getPlayerHand(String playerId){
            Player p = this.getPlayer(playerId);
            return p.getHand().getCards();
    }

    /**
     * Set the blind to the given set of cards
     * @param deck Set of cards to set blind to
     */
    public void setBlind(List<Card> deck) {
        this.blind = deck;
    }

    /**
     * Get the current player of the game
     * @return Current player of the game
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

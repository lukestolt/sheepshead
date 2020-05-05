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

    public Game(int numPlayers) {
        ugid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        tricks = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean isGameFull(){
        return players.size() == MAX_PLAYERS;
    }

    public void startGame() {
        if(!start) {
            start = true;
            SheepsheadDeck.deal(this);
            initDealer();
            dealBlind();
        }
    }

    private void dealBlind(){
        List<Card> pickerCards = this.currentPlayer.getHand().getCards();
        // adds the blind to the end of the hand
        pickerCards.addAll(this.blind);
    }

    public boolean isGameReady(){
        return this.start;
    }

    //TODO change to perform the action instead of enqueuing it
    // return trick,
    public AbstractResponse performAction(Action a) {
        switch(a.getAction()) {
            case "PlayCard": {
                AbstractResponse response;
                PlayCardAction pca = (PlayCardAction) a;
                Player p = getPlayer(a.getPlayerId());
                Card c = p.getCard(pca.getSuit(), pca.getValue());
                System.out.println(c.isTrumpSuit());
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
                            System.out.println(updatedTrick.getK().getUser().getUsername());
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
                Trick blindTrick = new Trick(MAX_PLAYERS);
                blindTrick.addCard(pba.cards[0], p);
                blindTrick.addCard(pba.cards[1], p);
                this.tricks.add(blindTrick);
                List<Card> cs = p.burryCards(blindTrick.getCards());
                Player nextTurn = players.get(nextPlayer(nextPlayer(players.indexOf(p))));
                currentPlayer = nextTurn;
                return new AcceptBlindResponse(pba.getPlayerId(), pba.getGameId());
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
//                return new BlindResponse(a.getPlayerId(), a.getGameId(), nextTurn.getUser().getUuid(),
//                                             p.getHand().getCards());
            }
            default:
                break;
        }
        return null;
    }

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
//            System.out.println(this.players.size());
            for(Player p: players) {
//                System.out.println(p.getUser().getUuid());
                if(p.getUser().getUuid().equals(playerId)) return p;
            }
            // Player ID doesn't exist
            return null;
        }
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getUGID() {
        return ugid;
    }

    @Override
    public String toString(){
        return players.toString();
    }

    public void initDealer() {
        int x = Util.getRandomIntBound(MAX_PLAYERS);
//        System.out.println("Dealer is Player: " + x);
//        System.out.println();
        dealer = players.get(x);
        currentPlayer = players.get(nextPlayer(x));
//        System.out.println(currentPlayer.getUser().getUuid());
    }

    public Player getPicker() {
        //TODO need to figure out how to assign picker
        // Via sockets
//        Scanner sc = new Scanner(System.in);
//        int ind = nextPlayer(players.indexOf(dealer));
//        int cnt = 0;
//        for(int i = ind; cnt < MAX_PLAYERS; i = nextPlayer(i)) {
//            System.out.println(players.get(i).getHand().toString());
//            System.out.println("Become Picker (y/n)?");
//            String inp = sc.nextLine();
//            if(inp.equals("y")) {
//                System.out.println();
//                System.out.println("Player: " + players.get(i).getUser().toString() + " is Picker");
//                Trick t = new Trick(MAX_PLAYERS);
//                for(int j = 0; j < blind.size(); j++) {
//                    t.addCard(blind.get(i), players.get(i));
//                }
//                players.get(i).wonTrick(t);
//                return players.get(i);
//            }
//            cnt++;
//        }
//        // Default to left of dealer
//        System.out.println("Player: " + players.get(players.indexOf(dealer)).getUser().toString() + " is Picker");
//        return players.get(nextPlayer(players.indexOf(dealer)));
        return null;
    }

    private int nextPlayer(int index) {
        if(index+1 < MAX_PLAYERS) {
            return index+1;
        } else {
            return 0;
        }
    }

    public List<Card> getPlayerHand(String playerId){
            Player p = this.getPlayer(playerId);
            return p.getHand().getCards();
    }

    public void setBlind(List<Card> deck) {
        this.blind = deck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.controller.game.AbstractResponse;
import com.capstone.sheepsheadbackend.controller.game.ErrorResponse;
import com.capstone.sheepsheadbackend.controller.game.PlayCardResponse;
import com.capstone.sheepsheadbackend.controller.game.WinningGameResponse;
import com.capstone.sheepsheadbackend.model.actions.Action;
import com.capstone.sheepsheadbackend.model.actions.PlayCardAction;
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
    private static boolean start;
    private Player currentPlayer;
    private boolean followSuit = false;

    public Game(int numPlayers) {
        ugid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        tricks = new ArrayList<>();
//        actions = new LinkedBlockingQueue<>();
    }

    public boolean addPlayer(Player player) {

        if(players.size() >= MAX_PLAYERS) {
            return false;
        } else {
            players.add(player);
            return true;
        }
    }

    public void start() {
        if(!start) {
            start = true;
        }
    }

    //TODO change to perform the action instead of enqueuing it
    // return trick,
    public AbstractResponse performAction(Action a) {
        switch(a.getAction()) {
            case "PlayCard":
                AbstractResponse response;
                PlayCardAction pca = (PlayCardAction)a;
                Player p = getPlayer(a.getPlayerId());
                Card c = p.getCard(pca.getSuit(), pca.getValue());
                if(currentTrick == null) {
                    currentTrick = new Trick(MAX_PLAYERS);
                    followSuit = c.isTrumpSuit();

                }
                Card ret = p.playCard(c, followSuit);
                if(ret == null) {
                    // bad player needs to resend
                    response = new ErrorResponse(a.getPlayerId(), a.getGameId(), "ERROR", "Invalid Card");
                } else {
                    // return new game state stuff
                    currentTrick.addCard(ret, p);
                    checkWonTrick();
                    response = checkGameOver();
                    if(response == null) {
                        response = new PlayCardResponse(a.getPlayerId(), a.getGameId(), p.getHand().getCards(), players.get(nextPlayer(nextPlayer(players.indexOf(p)))));
                    }
                }
                return response;
        }
        return null;
    }

    void checkWonTrick() {
        boolean sameNumCards = false;
        int numCardsInHand = players.get(0).getHand().getCards().size();
        for(int i = 1; i < players.size(); i++) {
            sameNumCards = numCardsInHand == players.get(i).getHand().getCards().size();
        }
        if(sameNumCards) {
            tricks.add(currentTrick);
            // Could probably send back to users to show who won the trick
            Player p = currentTrick.getWinner();
            currentTrick = null;
        }
    }

    AbstractResponse checkGameOver() {
        boolean noCards = true;
        for(int i = 0; i < players.size(); i++) {
            noCards = noCards && (players.get(i).getHand().getCards().isEmpty());
        }
        if(noCards) {
            Player p = getWinner();
            List<Integer> scores = players.stream().map(Player::getScore).collect(Collectors.toList());
            List<String> playerIds = players.stream().map(Player::getUser).map(User::getUuid).collect(Collectors.toList());
            return new WinningGameResponse(p.getUser().getUuid(), ugid, "Winner", p, scores, playerIds);
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
        for(Player p: players) {
            if(p.getUser().getUuid().equals(playerId)) return p;
        }
        // Player ID doesn't exist
        return null;
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

    public void setBlind(List<Card> deck) {
        this.blind = deck;
    }
}

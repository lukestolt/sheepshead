package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.model.actions.Action;
import com.capstone.sheepsheadbackend.model.actions.PlayCardAction;
import com.capstone.sheepsheadbackend.util.Util;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {

    private final String ugid;
    private List<Player> players;
    private List<Card> blind;
    private Player dealer;
    private Player picker;
    private final int MAX_PLAYERS;
    private static boolean start;
    private Queue<Action> actions;

    public Game(int numPlayers) {
        ugid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        actions = new LinkedBlockingQueue<>();
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
    public void performAction(Action a) {
        switch(a.getAction()) {
            case "PlayCard":
                PlayCardAction pca = (PlayCardAction)a;
                Player p = getPlayer(a.getPlayerId());
                Card c = p.getCard(pca.getSuit(), pca.getValue());
                Card ret = p.playCard(c);
                if(ret == null) {
                    // bad player needs to resend
                } else {
                    // return new game state stuff
                }
                break;
        }
    }

    private Player getPlayer(String playerId) {
        for(Player p: players) {
            if(p.getUser().getUuid().equals(playerId)) return p;
        }
        // Player ID doesn't exist
        return null;
    }

    public boolean enqueueAction(Action a) {
        return actions.offer(a);
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

//    public Trick playHands() {
//        Scanner sc = new Scanner(System.in);
//        Trick trick = new Trick(MAX_PLAYERS);
//        int ind = nextPlayer(players.indexOf(dealer));
//        int cnt = 0;
//        for(int i = ind; cnt < MAX_PLAYERS; i = nextPlayer(i)) {
//            Cardv2 c;
//            do {
//                System.out.println(players.get(i).getHand().toString());
//                System.out.println(players.get(i).toString() + ": Play a card (Must follow suit if can): ");
//                int card = sc.nextInt();
//                c = players.get(i).playCard(card);
//            } while(c == null);
//            trick.addCard(c,players.get(i));
//            cnt++;
//            System.out.println();
//        }
////        System.out.println(trick);
//        return trick;
//    }

    public void setBlind(List<Card> deck) {
        this.blind = deck;
    }
}

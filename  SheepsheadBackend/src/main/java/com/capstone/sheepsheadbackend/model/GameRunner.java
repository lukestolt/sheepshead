package com.capstone.sheepsheadbackend.model;

public class GameRunner implements Runnable {

    private Game game;

    private void gameLoop() {
        boolean active = true;
        if(game != null) {
            deal();
            assignDealer();
            //assignPicker8
            do {
                playHands();
                // something to determine trick winner
                //
            } while(active);
        }
    }

    private void playHands() {

    }

    private void assignDealer() {

    }

    private void deal() {
        game.dealHands();
    }

    @Override
    public void run() {

    }
}

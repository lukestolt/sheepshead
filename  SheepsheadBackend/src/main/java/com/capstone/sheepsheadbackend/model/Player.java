package com.capstone.sheepsheadbackend.model;

public class Player {
    private User user;
    private Hand hand;

    public Player(User user) {
        this.user = user;
    }

//    public Player() {
//        this.user = new GuestUser();
//    }

    public User getUser() {
        return user;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}

package com.capstone.sheepsheadbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private User user;
    private Hand hand;
    private List<Trick> tricks;

    public Player(User user) {
        this.user = user;
        tricks = new ArrayList<>();
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

    public void wonTrick(Trick trick) {
        tricks.add(trick);
    }

    @Override
    public String toString() {
        return user.toString();
    }
}

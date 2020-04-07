package com.capstone.sheepsheadbackend.controller;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

public class Action {
    public enum ActionType {
        PickBlind,
        PassBlind,
        CallSuit,
        PlayCard,
        ExitGame
    }

    public String action;
    public String playerId;
    public String gameId;
    public String suit;
    public String value;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

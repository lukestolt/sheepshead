package com.capstone.sheepsheadbackend.util;

public enum CardPointValue {
    ACE(11), KING(4), QUEEN(3), JACK(2),
    TEN(10), NINE(0), EIGHT(0), SEVEN(0);
    private int pointValue;
    CardPointValue(int i) {
        this.pointValue = i;
    }

    public int getPointValue() {
        return pointValue;
    }
}

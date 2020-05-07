package com.capstone.sheepsheadbackend.util;

public enum CardPointValue {
    ACE(11), KING(4), QUEEN(3), JACK(2),
    TEN(10), NINE(0), EIGHT(0), SEVEN(0);

    private int pointValue;

    /**
     * Create Card Point Value given by integer
     * @param i card point value
     */
    CardPointValue(int i) {
        this.pointValue = i;
    }

    /**
     * Get the point value as integer
     * @return point value
     */
    public int getPointValue() {
        return pointValue;
    }
}

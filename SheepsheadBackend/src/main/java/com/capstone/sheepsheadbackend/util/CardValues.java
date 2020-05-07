package com.capstone.sheepsheadbackend.util;

public enum CardValues {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"),
    TEN("10"), JACK("J"), QUEEN("Q"), KING("K"),
    ACE("A");

    private String strValue;

    /**
     * Create Card Values from Strings
     * @param strValue
     */
    CardValues(String strValue) {
        this.strValue = strValue;
    }

    /**
     * Get String values of Card Values
     * @return String card values
     */
    public String getStrValue() {
        return strValue;
    }
}

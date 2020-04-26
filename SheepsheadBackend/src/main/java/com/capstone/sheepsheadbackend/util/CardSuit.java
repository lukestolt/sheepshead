package com.capstone.sheepsheadbackend.util;

public enum CardSuit {
    HEARTS(CardStrength.FAILSUIT, 2, "H"), CLUBS(CardStrength.FAILSUIT, 4, "C"),
    DIAMONDS(CardStrength.TRUMPSUIT, 1, "D"), SPADES(CardStrength.FAILSUIT, 3, "S");

    private CardStrength cardStrength;
    private int cardScore;
    private String strSuit;

    CardSuit(CardStrength cardStrength, int cardScore, String strSuit) {
        this.cardStrength = cardStrength;
        this.cardScore = cardScore;
        this.strSuit = strSuit;
    }

    public CardStrength getCardStrength() {
        return cardStrength;
    }
    public int getCardScore() { return cardScore; }
    public String getStrSuit() { return strSuit; }

    public static CardSuit fromStrSuit(String stringSuit) {
        for(CardSuit s: CardSuit.values()) {
            if(s.strSuit.equals(stringSuit)) return s;
        }
        throw new IllegalStateException("Literally cannot happen");
    }
}

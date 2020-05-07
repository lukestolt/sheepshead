package com.capstone.sheepsheadbackend.util;

public enum CardSuit {
    HEARTS(CardStrength.FAILSUIT, 2, "H"), CLUBS(CardStrength.FAILSUIT, 4, "C"),
    DIAMONDS(CardStrength.TRUMPSUIT, 1, "D"), SPADES(CardStrength.FAILSUIT, 3, "S");

    private CardStrength cardStrength;
    private int cardScore;
    private String strSuit;

    /**
     * Create card suit
     * @param cardStrength Trump or Fail suit
     * @param cardScore Rank of suit
     * @param strSuit Suit as String
     */
    CardSuit(CardStrength cardStrength, int cardScore, String strSuit) {
        this.cardStrength = cardStrength;
        this.cardScore = cardScore;
        this.strSuit = strSuit;
    }

    /**
     * Get the card strength of the suit
     * @return Trump or Fail suit
     */
    public CardStrength getCardStrength() {
        return cardStrength;
    }

    /**
     * Get the card score of the Suit
     * @return rank of the suit
     */
    public int getCardScore() { return cardScore; }

    /**
     * Get Suit as String
     * @return Suit as String
     */
    public String getStrSuit() { return strSuit; }

    /**
     * Get Card Suit from String
     * @param stringSuit Suit as String
     * @return Card suit from String
     */
    public static CardSuit fromStrSuit(String stringSuit) {
        for(CardSuit s: CardSuit.values()) {
            if(s.strSuit.equals(stringSuit)) return s;
        }
        throw new IllegalStateException("Literally cannot happen");
    }
}

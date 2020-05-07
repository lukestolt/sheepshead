package com.capstone.sheepsheadbackend.util;

public enum SheepsheadCardValue {
    SEVEN("7", CardPointValue.SEVEN, CardStrength.FAILSUIT, 1), EIGHT("8", CardPointValue.EIGHT, CardStrength.FAILSUIT, 2),
    NINE("9", CardPointValue.NINE, CardStrength.FAILSUIT, 3), TEN("10", CardPointValue.TEN, CardStrength.FAILSUIT, 5),
    JACK("J", CardPointValue.JACK, CardStrength.TRUMPSUIT, 7), QUEEN("Q", CardPointValue.QUEEN, CardStrength.TRUMPSUIT, 8),
    KING("K", CardPointValue.KING, CardStrength.FAILSUIT, 4), ACE("A", CardPointValue.ACE, CardStrength.FAILSUIT, 6);

    private String strValue;
    private CardPointValue pointValue;
    private CardStrength cardStrength;
    private int biasedValue;

    /**
     * Create Card value for the game of sheepshead
     * @param strValue Card Value as String
     * @param pointValue Point value of the card
     * @param cardStrength Trump or Fail suit
     * @param biasedValue Biased value of the cards
     */
    SheepsheadCardValue(String strValue, CardPointValue pointValue, CardStrength cardStrength, int biasedValue) {
        this.strValue = strValue;
        this.pointValue = pointValue;
        this.cardStrength = cardStrength;
        this.biasedValue = biasedValue;
    }

    /**
     * Get the value as a String
     * @return Value as a String
     */
    public String getStrValue() {
        return strValue;
    }

    /**
     * Get the point Value
     * @return Point value of the card value
     */
    public CardPointValue getPointValue() {
        return pointValue;
    }

    /**
     * Get the Strength of the card value
     * @return Trump or Fail suit
     */
    public CardStrength getCardStrength() { return cardStrength; }

    /**
     * Get the Biased value of the card value
     * @return
     */
    public int getBiasedValue() { return biasedValue; }

    /**
     * Get the Sheepshead Card Value from String
     * @param stringValue Value to convert from
     * @return Sheepshead Card value
     */
    public static SheepsheadCardValue fromStrValue(String stringValue) {
        for(SheepsheadCardValue v: SheepsheadCardValue.values()) {
            if(v.strValue.equals(stringValue)) return v;
        }
        throw new IllegalStateException("Literally cannot happen");
    }
}

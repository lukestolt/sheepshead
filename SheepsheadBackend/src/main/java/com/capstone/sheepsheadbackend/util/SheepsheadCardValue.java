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
     *
     * @param strValue
     * @param pointValue
     * @param cardStrength
     * @param biasedValue
     */
    SheepsheadCardValue(String strValue, CardPointValue pointValue, CardStrength cardStrength, int biasedValue) {
        this.strValue = strValue;
        this.pointValue = pointValue;
        this.cardStrength = cardStrength;
        this.biasedValue = biasedValue;
    }

    /**
     *
     * @return
     */
    public String getStrValue() {
        return strValue;
    }

    /**
     *
     * @return
     */
    public CardPointValue getPointValue() {
        return pointValue;
    }

    /**
     *
     * @return
     */
    public CardStrength getCardStrength() { return cardStrength; }

    /**
     *
     * @return
     */
    public int getBiasedValue() { return biasedValue; }

    /**
     *
     * @param stringValue
     * @return
     */
    public static SheepsheadCardValue fromStrValue(String stringValue) {
        for(SheepsheadCardValue v: SheepsheadCardValue.values()) {
            if(v.strValue.equals(stringValue)) return v;
        }
        throw new IllegalStateException("Literally cannot happen");
    }
}

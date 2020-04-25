package com.capstone.sheepsheadbackend.util;

public enum SheepsheadCardValue {
    SEVEN("7", CardPointValue.SEVEN, CardStrength.FAILSUIT), EIGHT("8", CardPointValue.EIGHT, CardStrength.FAILSUIT),
    NINE("9", CardPointValue.NINE, CardStrength.FAILSUIT), TEN("10", CardPointValue.TEN, CardStrength.FAILSUIT),
    JACK("J", CardPointValue.JACK, CardStrength.TRUMPSUIT), QUEEN("Q", CardPointValue.QUEEN, CardStrength.TRUMPSUIT),
    KING("K", CardPointValue.KING, CardStrength.FAILSUIT), ACE("A", CardPointValue.ACE, CardStrength.FAILSUIT);

    private String strValue;
    private CardPointValue pointValue;
    private CardStrength cardStrength;

    SheepsheadCardValue(String strValue, CardPointValue pointValue, CardStrength cardStrength) {
        this.strValue = strValue;
        this.pointValue = pointValue;
        this.cardStrength = cardStrength;
    }

    public String getStrValue() {
        return strValue;
    }

    public CardPointValue getPointValue() {
        return pointValue;
    }

    public CardStrength getCardStrength() {
        return cardStrength;
    }
}

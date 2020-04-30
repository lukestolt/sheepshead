package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.CardStrength;
import com.capstone.sheepsheadbackend.util.CardSuit;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final String suit;
    private final String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public boolean isTrumpSuit() {
        SheepsheadCardValue val = SheepsheadCardValue.fromStrValue(value);
        CardSuit s = CardSuit.fromStrSuit(suit);
        return val.getCardStrength() == CardStrength.TRUMPSUIT ||
                s.getCardStrength() == CardStrength.TRUMPSUIT;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit,value);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Card) {
            Card c = (Card)o;
            return this.suit.equals(c.suit) && this.value.equals(c.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return "<" + suit + "," + value + ">";
    }

    @Override
    public int compareTo(Card card) {
        boolean trumpA = this.isTrumpSuit();
        boolean trumpB = card.isTrumpSuit();

        SheepsheadCardValue valA = SheepsheadCardValue.fromStrValue(value);
        CardSuit sA = CardSuit.fromStrSuit(suit);

        SheepsheadCardValue valB = SheepsheadCardValue.fromStrValue(card.value);
        CardSuit sB = CardSuit.fromStrSuit(card.suit);

        if(trumpA && trumpB) {
            return compareCards(valA.getBiasedValue(), sA.getCardScore(), -1,
                    valB.getBiasedValue(), sB.getCardScore(), -1);
        } else if(trumpA == false && trumpB == false) {
            return compareCards(valA.getPointValue().getPointValue(), sA.getCardScore(), valA.getBiasedValue(),
                    valB.getPointValue().getPointValue(), sB.getCardScore(), valB.getBiasedValue());
        } else {
            if(trumpA) return -1;
            else return 1;
        }
    }

    private int compareCards(int cardAVal1, int cardAVal2, int cardAVal3, int cardBVal1, int cardBVal2, int cardBVal3) {
        if(cardAVal1 > cardBVal1) {
            return -1;
        } else if(cardAVal1 < cardBVal1) {
            return 1;
        } else {
            if(cardAVal2 > cardBVal2) {
                return -1;
            } else if(cardAVal2 < cardBVal2) {
                return 1;
            } else {
                if(cardAVal3 > cardBVal3) {
                    return -1;
                } else if(cardAVal3 < cardBVal3){
                    return 1;
                } else {
                    throw new IllegalStateException("Literally not possible");
                }
            }
        }
    }
}

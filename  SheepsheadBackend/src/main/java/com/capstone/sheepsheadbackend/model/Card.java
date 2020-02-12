package com.capstone.sheepsheadbackend.model;

import com.capstone.sheepsheadbackend.util.CardStrength;
import com.capstone.sheepsheadbackend.util.CardSuits;
import com.capstone.sheepsheadbackend.util.SheepsheadCardValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Data
@JsonDeserialize(builder = Card.CardBuilder.class)
@Builder(builderClassName = "CardBuilder", toBuilder = true)
public final class Card {
    private final SheepsheadCardValue val;
    private final CardSuits suit;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class CardBuilder {
    }

    public static boolean isTrumpSuit(Card card) {
        return card.getVal().getCardStrength() == CardStrength.TRUMPSUIT
                || card.getSuit().getCardStrength() == CardStrength.TRUMPSUIT;
    }
}

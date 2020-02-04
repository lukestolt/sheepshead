package com.capstone.sheepsheadbackend.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@JsonDeserialize(builder = Card.CardBuilder.class)
@Builder(builderClassName = "CardBuilder", toBuilder = true)
public final class Card {
    private final String val;
    private final String suit;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class CardBuilder {
    }

//    public Card(String value, String suit) {
//        this.val = value;
//        this.suit = suit;
//    }

    @Override
    public String toString() {
        return "("+suit+","+val+")";
    }
}

package com.capstone.sheepsheadbackend.model.actions;
import com.capstone.sheepsheadbackend.model.game.SimpleCard;
import com.capstone.sheepsheadbackend.model.Card;
import com.capstone.sheepsheadbackend.model.GamesManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("PickAction")
public class BlindAction extends Action {
    // should only be the size of 2 since the game only allows 3 players
    public SimpleCard[] burriedCards;
    @JsonIgnore
    public Card[] cards;

    /**
     *
     * @param action
     * @param playerId
     * @param gameId
     * @param burriedCards
     */
    public BlindAction(String action, String playerId, String gameId, SimpleCard[] burriedCards){
        super(action, gameId, playerId);
        this.burriedCards = burriedCards;
    }

    /**
     *
     */
    public BlindAction(){
        super();
    }

    /**
     *
     * @return
     */
    public SimpleCard[] getBurriedCards() {
        return burriedCards;
    }

    /**
     *
     * @param burriedCards
     */
    public void setBurriedCards(SimpleCard[] burriedCards){
        this.burriedCards = burriedCards;
    }

    /**
     *
     */
    public void convertCards() {
        if(burriedCards != null){
            cards = new Card[2];
            cards[0] = new Card(burriedCards[0].getSuit(), burriedCards[0].getValue());
            cards[1] = new Card(burriedCards[1].getSuit(), burriedCards[1].getValue());
        }

    }







    @Override
    public void perform(GamesManager gm) {
        gm.addAction(this);
    }
}

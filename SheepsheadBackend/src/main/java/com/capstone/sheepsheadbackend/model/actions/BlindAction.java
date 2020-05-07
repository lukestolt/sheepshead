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
     * Create new Blind Action
     * @param action Action as String
     * @param playerId Player Id of action
     * @param gameId Game Id of action
     * @param burriedCards Blind Cards
     */
    public BlindAction(String action, String playerId, String gameId, SimpleCard[] burriedCards){
        super(action, gameId, playerId);
        this.burriedCards = burriedCards;
    }

    /**
     * Create new Blind Action
     */
    public BlindAction(){
        super();
    }

    /**
     * Get Blind Cards
     * @return Blind Cards
     */
    public SimpleCard[] getBurriedCards() {
        return burriedCards;
    }

    /**
     * Set Blind Cards
     * @param burriedCards Blind Cards
     */
    public void setBurriedCards(SimpleCard[] burriedCards){
        this.burriedCards = burriedCards;
    }

    /**
     * Convert Blind Cards to Game Cards
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

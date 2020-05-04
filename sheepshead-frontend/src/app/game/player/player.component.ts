import { Component, Input } from '@angular/core';
import { Card } from 'src/app/models/card';
import { Player } from 'src/app/models/player';
import { CardAction, GameService, ActionType } from 'src/app/services/game.service';
import { GameComponent } from '../game.component';
import { PlayerDataService } from 'src/app/services/player-data.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent{

  @Input() player: Player;
  @Input() gameId: string;
  @Input() isBlindState: boolean;
  blindCards: Card[] = [];
  curPlayerId: String;

  constructor(private gameService:GameService) {
    
  }
 
  getCardName(card: Card): string {
    return this.gameService.getCardName(card);
  }


  /**
   * can send card played action or a blind accept or decline
   * @param clickedCard 
   */
  cardClick(clickedCard: Card): void {
    if(this.player.isTurn) {
      if(this.isBlindState){
        // if have too many cards selected for blind remove the first one selected
        if(this.blindCards.length >= 2){
          // ignore the clicked card if it is already in the array
          if(clickedCard === this.blindCards[1] || clickedCard === this.blindCards[0])
            return;
          const tmp = this.blindCards[1];
          this.blindCards[0] = tmp;
          this.blindCards[1] = clickedCard;
        } else {
          this.blindCards.push(clickedCard);
        }
      }
      // playing the game state
      else 
      {
        const action:CardAction = {action: ActionType.PlayCard,playerId: this.player.id,
                                   gameId: this.gameService.getGameId(), suit: clickedCard.suit,
                                   value: clickedCard.value};
        this.gameService.sendPlayerAction(action).subscribe(result => {
          if(result && result.responseType !== 'ERROR'){
            this.player.cards = result.cards
            //TODO: show a message telling the user to play the correct type
          }
        });
      }      
    } 
  }

  isBelongToBlind(c: Card): boolean {
    return (c === this.blindCards[0] || c === this.blindCards[1]);
  }

  cardMouseHover(card: Card) {
    card.isHovered = true;
  }

  cardMouseOut(card: Card) {
    card.isHovered = false;
  }
}

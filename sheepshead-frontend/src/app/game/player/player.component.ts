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
  curPlayerId: String;

  constructor(private gameService:GameService) {
    
  }
 
  getCardName(card: Card): string {
    return this.gameService.getCardName(card);
  }

  cardClick(clickedCard: Card): void {
    if(this.player.isTurn) {
      const action:CardAction = {action: ActionType.PlayCard,playerId: this.player.id, gameId: this.gameService.getGameId(), suit: clickedCard.suit, value: clickedCard.value};
      // the game service should have the ws
      this.gameService.sendPlayerAction(action).subscribe(result => {
        if(result && result.responseType !== 'ERROR'){
          this.player.cards = result.cards
          //tODO: show a message telling the user to play the correct type
        }
        
      });
    } 
  }

  cardMouseHover(card: Card) {
    card.isHovered = true;
  }
  cardMouseOut(card: Card) {
    card.isHovered = false;
  }
}

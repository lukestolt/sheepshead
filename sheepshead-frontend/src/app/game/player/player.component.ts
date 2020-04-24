import { Component, Input } from '@angular/core';
import { Card } from 'src/app/models/card';
import { Player } from 'src/app/models/player';
import { CardAction, GameService, ActionType } from 'src/app/services/game.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent{

  @Input() player: Player;
  @Input() gameId: string;

  constructor(private gameService:GameService) {
  }

  getCardName(card: Card): string {
    return this.gameService.getCardName(card);
  }


  cardClick(clickedCard: Card): void {
    console.log(clickedCard);
    const action:CardAction = {action: ActionType.PlayCard,playerId: this.player.id, gameId: this.gameId, suit: clickedCard.suit, value: clickedCard.value};
    // the game service should have the ws
    this.gameService.sendPlayerAction(action).subscribe(result => {
      // the result should update the currentplayer
      // the result should 
      console.log(result);
    });
  }

  cardMouseHover(card: Card) {
    card.isHovered = true;
  }
  cardMouseOut(card: Card) {
    card.isHovered = false;
  }
}

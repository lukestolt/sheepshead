import { Component, Input } from '@angular/core';
import { Card } from 'src/app/models/card';
import { Player } from 'src/app/models/player';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent{

  @Input() player: Player;

  constructor() {
    console.log(this.player);
  }

  getCardName(card: Card): string {
    console.log(card);
    if(!card)
      return ''
    let path = '../assets/cards/';
    path = path.concat(card.value.charAt(0).toLocaleUpperCase());
    path = path.concat(card.suit.toLocaleUpperCase());
    path = path.concat('.png');
    return path;
  }

  clickCard(card:Card): void {
    
  }



}

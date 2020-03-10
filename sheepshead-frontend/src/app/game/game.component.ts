import { Component, OnInit } from '@angular/core';
import { Player } from '../models/player';
import { Card } from '../models/card';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  private opponents: Player[];
  private curPlayer: Player;

  constructor() {
    this.curPlayer = this.generatePlayerData();
  }

  ngOnInit() {
  }

  /**
   * tmp method to create player data
   */
  private generatePlayerData(): Player {
    const p = new Player('p1');
    p.addCards(this.generateCards(2));
    return p;
    
  }

  /**
   * tmp method for data
   * @param handSize the size of the hand
   */
  private generateCards(handSize: number): Card[]
  {
    // for(let i = 0; i < handSize; i++) {
    //   // generate that many cards here
    // }
    const hand = [];
    hand.push(new Card('s','ace'));
    hand.push(new Card('d','king'));
    return hand;
  }

  getCardName(card: Card): string {
    let path = '../assets/cards/';
    path = path.concat(card.value.charAt(0).toLocaleUpperCase());
    path = path.concat(card.suit.toLocaleUpperCase());
    path = path.concat('.png');
    return path;
  }


}

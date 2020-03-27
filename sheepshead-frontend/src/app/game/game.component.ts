import { Component, OnInit } from '@angular/core';
import { Card } from '../models/card';
import { PlayerComponent } from './player/player.component';
import { Player } from '../models/player';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  private opponents: Player[];
  private curplayer: Player;
  private curPlayerTurn: string;    // id of the current player

  constructor() {
    this.curplayer = this.generatePlayerData();
  }

  ngOnInit() {
  }

  /**
   * tmp method to create player data
   */
  private generatePlayerData(): Player {
    const p = new Player('p1', this.generateCards());
    return p;
  }

  /**
   * tmp method for data
   * @param handSize the size of the hand
   */
  private generateCards(): Card[]
  {
    const hand = [];
    hand.push(new Card('s','ace'));
    hand.push(new Card('d','king'));
    return hand;
  }

  isPlayerTurn(): boolean {
    return this.curplayer.id === this.curPlayerTurn;
  }

}

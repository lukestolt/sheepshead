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
  private gameId: string;

  constructor() {
    this.curplayer = this.generatePlayerData();
    this.generateOpponentData(2);
    this.gameId = 'random-game-id-1'
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

  /***
   * tmp method to create mock data
   * @param numPlayers number of opponents to create
   */
  private generateOpponentData(numPlayers: number):void {
    this.opponents = [];
    for(let x = 0; x < numPlayers; ++x) {
      //TODO: hardcoded number of cards should no be hardcoded
      this.opponents.push(new Player('p' + (x+2), null, 2));
    }

  }

  isPlayerTurn(): boolean {
    return this.curplayer.id === this.curPlayerTurn;
  }

}

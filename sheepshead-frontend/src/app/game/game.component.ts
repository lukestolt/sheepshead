import { Component, OnInit } from '@angular/core';
import { Card } from '../models/card';
import { Player } from '../models/player';
import { PlayerDataService } from '../services/player-data.service';
import { GameService } from '../services/game.service';
import { WebSocketApi } from '../web-socket/web-socket-api';
import { take } from 'rxjs/operators';
import { PlayerComponent } from './player/player.component';



export interface Response {
  playerId: string;
  gameId: string;
  responseType: string;
}
export interface PlayCardResponse extends Response{
  numCards: number;
  suit: string;
  value: string;
}
export interface testResponse extends Response{

}

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
  private curTrick: Card[] = [];
  private webS: WebSocketApi = new WebSocketApi();

  constructor(private _playerService:PlayerDataService, private gameService:GameService) {
    this.curplayer = this.generatePlayerData();
    this.generateOpponentData(2);
    this.gameId = 'random-game-id-1'

    // this._gameService.findGame(this._playerService.getPlayerId()).pipe(take(1)).subscribe(r => {
    //   this.gameId = r.gameId;
    //   this.webS.endpoint = r.wsIp;
    // });
    this.webS.connect().subscribe(action => {
      if(action) {
        this.handleServerEvent(JSON.parse(action.body));
      }
    });
  }

  ngOnInit() {
  }

  handleServerEvent(action: any): void{
    // console.log(action);
    if(action) {
      // check the discriminator property
      if(action.responseType === 'PlayCardResponse'){
        const pcr = action as PlayCardResponse
        // find the person that played the card and reduce cards and put their card in trick pile
        for(let opp of this.opponents) {
          if(opp.id === pcr.playerId) {
            console.log('updating ' + opp.id);
            opp.numCards = pcr.numCards;
            this.curTrick.push(new Card(pcr.suit,pcr.value));
            return;
          }
        }
      }
      
    }
  }

  getCardName(card: Card): string {
    return this.gameService.getCardName(card);
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

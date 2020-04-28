import { Component, OnInit } from '@angular/core';
import { Card } from '../models/card';
import { Player } from '../models/player';
import { PlayerDataService } from '../services/player-data.service';
import { GameService } from '../services/game.service';
import { WebSocketApi } from '../web-socket/web-socket-api';
import { take } from 'rxjs/operators';
import { PlayerComponent } from './player/player.component';
import { Observable } from 'rxjs';



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

  constructor(private _playerService:PlayerDataService, private gameService:GameService) {
    this.curplayer = _playerService.player;

    /**
     * this will be populated one the game starts
     */
    this.gameService.ws.getInitGameInfo(this.curplayer.id).subscribe(info => {
      info = JSON.parse(info);
      if(info != null){
        let oppsNames = info.oppNames;
        let oppIds = info.oppIds;
        let curPl = info.startingPlayer;

        this.curPlayerTurn = curPl;
        if(this.curplayer.id === curPl) {
          this.curplayer.isTurn = true;
        } else {
          this.curplayer.isTurn = false;
        }

        this.opponents = [];
        if(oppsNames != null && oppIds != null){
          const names: string[] = oppsNames;
          const ids: string[] = oppIds;          
          for(let i=0; i < oppsNames.length; i++){
            this.opponents.push(new Player(oppIds[i], names[i], null));
          }
        }
  
        const cards: Card[] = info.cards;
        if(cards){
          this.curplayer.cards = cards;
          this.opponents.forEach(opp => {
            opp.numCards = cards.length;   
          });
        }
      }
    });
    this.gameService.playerReady().subscribe(()=>{});

    /***
     * handles the response that is broadcasted from the server when a player plays a valid card
     */
    this.gameService.ws.getActionResponse().subscribe(info => {

      if(info == null) return;
      info = JSON.parse(info);
      
      switch(info.responseType) {
        case "ERROR":
            console.log("ERROR");
          break;
        case "validCard":
            this.opponents.forEach(opp => {
              if(info.playerId === opp.id) {
                opp.numCards--;
              }
            });
            let nextId = info.nextTurnId;
            this.curPlayerTurn = nextId;
            console.log('next persons turn = ' + nextId);
            if(this.curplayer.id === nextId) {
              this.curplayer.isTurn = true;
            } else {
              this.curplayer.isTurn = false;
            }
            this.curTrick = info.trick;
          break;
      }
    });
  }

  ngOnInit() {
  }

  handleServerEvent(action: Response): void{
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

  private generateOpponentData(numPlayers: number):void {
    this.opponents = [];
    for(let x = 0; x < numPlayers; ++x) {
      //TODO: hardcoded number of cards should no be hardcoded
      this.opponents.push(new Player('randid' + x+2, ('p' + (x+2)), null,));
    }
  }

  private isPlayerTurn(): boolean {
    return this.curplayer.id === this.curPlayerTurn;
  }

}

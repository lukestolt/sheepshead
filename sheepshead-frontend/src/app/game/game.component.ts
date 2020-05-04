import { Component, OnInit } from '@angular/core';
import { Card } from '../models/card';
import { Player } from '../models/player';
import { PlayerDataService } from '../services/player-data.service';
import { GameService, SheepsheadAction, BlindAction, ActionType } from '../services/game.service';
import { WebSocketApi } from '../web-socket/web-socket-api';
import { take } from 'rxjs/operators';
import { PlayerComponent } from './player/player.component';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { WinGameDialogComponent } from '../win-game-dialog/win-game-dialog.component';
import { writeFile } from 'fs';
import { Router } from '@angular/router';
import { Action } from 'rxjs/internal/scheduler/Action';



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

export interface WinGameResponse extends Response {
  winnerName: string;
  playerPoints: number[];
  playerNames: string[];
}

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})

export class GameComponent {
  private opponents: Player[];
  private curplayer: Player;
  private curPlayerTurn: string;    // id of the current player
  private gameId: string;
  private curTrick: Card[] = [];
  private isBlindState: boolean = true;

  constructor(private _playerService:PlayerDataService,
     private gameService:GameService,
      public dialog: MatDialog,
      private route: Router) {
    this.curplayer = _playerService.player;

    /**
     * this will be populated one the game starts
     */
    this.gameService.ws.getInitGameInfo(this.curplayer.id).subscribe(info => {
      info = JSON.parse(info);
      if(info != null){
        switch(info.responseType){
          case "gameInit":
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
          break;
          // // Card[]
          // // picker
          // case "blindInfo":
          //   if(this.curplayer.id === info.pickerId){
          //     this.blind = info.blind;
          //     // highlight the last two cards and show the accept decline ui
          //   }
          //   // highlight the cards in the trick should be the last two cards
          //   info.trick
          // break;
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
            this.setPlayerTurn(info.nextTurnId);
            this.curTrick = info.trick;
          break;
        case 'winGame':
            const wgr = info as WinGameResponse;
            // this is workaround for the ui
            this.curplayer.cards = [];
            this.curplayer.isTurn =false;
            const results = {winnerName: wgr.winnerName, names: wgr.playerNames, points: wgr.playerPoints};
            this.openDialog(results);
          break;
        // should contain whose turn it is
        case 'pickBlind':
          this.setPlayerTurn(info.nextTurnId);
          this.isBlindState = false;
          break;
      }
    });
  }

  clickAccept(): void{
    // TODO: send real cards
    console.log(ActionType.PickBlind.toString());
    const a: BlindAction = { action: ActionType.PickBlind.toString(), gameId: this.gameService.getGameId(), playerId: this.curplayer.id, burriedCards: []};
    this.gameService.sendPlayerAction(a).subscribe(result => {
      console.log(result);
    });
  }

  clickPass(): void {
  const a: BlindAction = { action: ActionType.PassBlind.toString(), gameId: this.gameService.getGameId(), playerId: this.curplayer.id, burriedCards: null};
  this.gameService.sendPlayerAction(a).subscribe(result => {
    console.log(result);
  });
  }

  setPlayerTurn(playerId: string): void {
    this.opponents.forEach(opp => {
      if(playerId === opp.id) {
        opp.isTurn = true;
      }
    });

    this.curPlayerTurn = playerId;

    console.log('next persons turn = ' + playerId);
    if(this.curplayer.id === playerId) {
      this.curplayer.isTurn = true;
    } else {
      this.curplayer.isTurn = false;
    }
  }

  handleServerEvent(action: Response): void{
    
    if(action) {
      // check the discriminator property 
      if(action.responseType === 'PlayCardResponse'){
        const pcr = action as PlayCardResponse
        // find the person that played the card and reduce cards and put their card in trick pile
        for(let opp of this.opponents) {
          if(opp.id === pcr.playerId) {
            opp.numCards = pcr.numCards;
            this.curTrick.push(new Card(pcr.suit,pcr.value));
            return;
          }
        }
      }
    }
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(WinGameDialogComponent, {
      width: '400px',
      height: '300px',
      data: data
    });

    // navigate back to the home screen and tell the server to stop commincating
    dialogRef.afterClosed().subscribe(result => {
      this.route.navigateByUrl('/home');
     
    });
  }

  getCardName(card: Card): string {
    return this.gameService.getCardName(card);
  }

  private isPlayerTurn(): boolean {
    return this.curplayer.id === this.curPlayerTurn;
  }

}

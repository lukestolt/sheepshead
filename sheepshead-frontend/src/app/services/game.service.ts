import { Injectable } from '@angular/core';
import { Card } from 'src/app/models/card';
import { HttpClient } from '@angular/common/http';
import { ServerConfig } from './server-config';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
  })
export class GameService {
    
    constructor(private http:HttpClient){  }
 
    /**
     * retruns the ip address of the ws and the gameId
     * @param playerId 
     */
    findGame(playerId: String): Observable<FindGameResults> {
        console.log('Searching for game');
        return this.http.put<FindGameResults>(ServerConfig.serverUrl + '/findGame', playerId);
    }

    sendPlayerAction(action: SheepsheadAction): Observable<any> {
        return this.http.post(ServerConfig.serverUrl + '/sendPlayerAction', action);
    }

    getCardName(card: Card): string {
        if(!card)
          return ''
        let path = '../assets/cards/';
        path = path.concat(card.value.charAt(0).toLocaleUpperCase());
        path = path.concat(card.suit.toLocaleUpperCase());
        path = path.concat('.png');
        return path;
    }
}

export interface CardAction extends SheepsheadAction {
    action: ActionType
    suit: string;
    value: string
}

export interface SheepsheadAction {
    playerId: string;
    gameId: string;
}

export enum ActionType{
    PickBlind = "PickBlind",
    PassBlind = "PassBlind",
    CallSuit = "CallSuit",
    PlayCard = "PlayCard",
    ExitGame = "ExitGame"
}

// RESULTS
export interface FindGameResults {
    wsIp: string;
    gameId: string;
}





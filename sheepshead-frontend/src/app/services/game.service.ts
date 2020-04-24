import { Injectable } from '@angular/core';
import { Card } from 'src/app/models/card';
import { HttpClient } from '@angular/common/http';
import { ServerConfig } from './server-config';
import { Observable } from 'rxjs';
import { WebSocketApi } from '../web-socket/web-socket-api';


export interface GameSearchParams{
    playerId: string;
    numPlayerOptions: string[];
}

@Injectable({
    providedIn: 'root'
  })
export class GameService {

    private ws: WebSocketApi;
    private serverRespone: Observable<any>;
    private gameId: string;
    
    constructor(private http:HttpClient){}
 
    /**
     * retruns the ip address of the ws and the gameId
     * @param gameSettings
     */
    // TODO: add the gameSettings as params
    findGame(pId: string, gameSettings: any): void{
        console.log('Searching for game');
        const params = {"playerId": pId, "numPlayerOptions": gameSettings};
        this.http.post<any>(ServerConfig.serverUrl + '/findGame', params).subscribe(gId => {
            this.gameId = gId;
        });
        this.ws = new WebSocketApi();
        this.serverRespone = this.ws.connect();
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

    getServerResponse(): Observable<any> {
        return this.serverRespone;
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



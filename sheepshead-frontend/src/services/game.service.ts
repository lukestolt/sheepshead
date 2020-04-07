import { Injectable } from '@angular/core';
import { Card } from 'src/app/models/card';
import { HttpClient } from '@angular/common/http';
import { ServerConfig } from './server-config';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
  })
export class GameService {

    constructor(private http:HttpClient){

    }
    
    sendPlayerAction(action: SheepsheadAction): Observable<any> {
        return this.http.post(ServerConfig.serverUrl + '/sendPlayerAction', action);
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





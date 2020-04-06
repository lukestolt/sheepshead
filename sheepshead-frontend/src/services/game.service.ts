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
    
    sendPlayerAction(action:CardAction): Observable<any> {
        console.log(action);
        return this.http.post(ServerConfig.serverUrl + '/sendPlayerAction', 'hello from client');
    }

}

export interface CardAction {
    action: ActionType
    playerId: string;
    gameId: string;
    card: Card;
}

export enum ActionType{
    PickBlind = "PickBlind",
    PassBlind = "PassBlind",
    CallSuit = "CallSuit",
    PlayCard = "PlayCard",
    ExitGame = "ExitGame"
}


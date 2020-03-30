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

    
    sendPlayerCardAction(action:CardAction): Observable<any> {
        return this.http.post(ServerConfig.serverUrl + '/playerCardAction', action);
    }



}

export interface CardAction {
    playerId: string;
    gameId: string;
    card: Card;
}


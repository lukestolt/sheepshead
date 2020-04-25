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
    private gameStatusResponse: Observable<any> = new Observable<any>();
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
            // after it finds a game to put the player in
            // the player should wait until the game is full 
            // and then should request their cards for the game

            this.ws = new WebSocketApi(this);
            //TODO: remove this when logic is implemented on the server
            this.gameReady().subscribe(result => {
                console.log(result);
            });
            // this.gameStatusResponse = this.ws.getGameInfo();
        });
    }

    /**
     * should return intial hand for the player
     * @param pId - the player id
     * @param gameId - the game id
     */
    getHand(pId: string): Observable<any> {
        return this.http.get<any>(ServerConfig.serverUrl + '/getHand', {params: { playerId: pId, gameId: this.gameId}});
    }

    /*
     * The result only affects the caller and all the other clients get pushed an update from the subscription
     * @param action 
     */
    sendPlayerAction(action: SheepsheadAction): Observable<any> {
        return this.http.post(ServerConfig.serverUrl + '/gameAction', action);
    }

    // TODO: remove this when logic is integrated with server
    // this is used to tell the client that the game is ready when in reality the server logic should 
    // call the method on its side
    gameReady(): Observable<any>{
        return this.http.get<any>(ServerConfig.serverUrl + '/gamestatus');
    }

    getCardName(card: Card): string {
        if(!card)
          return ''
        let path = '../assets/cards/';
        path = path.concat(card.value.toLocaleUpperCase());
        path = path.concat(card.suit.toLocaleUpperCase());
        path = path.concat('.png');
        return path;
    }

    getGameStatusResponse(): Observable<any> {
        return this.gameStatusResponse;
    }

    getGameId(): string {
        return this.gameId;
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



import { Injectable } from '@angular/core';
import { Card } from 'src/app/models/card';
import { HttpClient } from '@angular/common/http';
import { ServerConfig } from './server-config';
import { Observable, BehaviorSubject } from 'rxjs';
import { WebSocketApi } from '../web-socket/web-socket-api';
import { Player } from '../models/player';
import { basename } from 'path';


export interface GameSearchParams{
    playerId: string;
    numPlayerOptions: string[];
}

export interface InitGameData{
    cards: Card[];
    turnPlayerId: string;
}

export interface OpponentData{
    opponentNames: string[];
}



@Injectable({
    providedIn: 'root'
  })
export class GameService {

    public ws: WebSocketApi;
    private gameId: string;
    private bs:BehaviorSubject<any> = new BehaviorSubject<any>(null);
    
    constructor(private http:HttpClient){}
 
    /**
     * retruns the ip address of the ws and the gameId
     * @param gameSettings
     */
    findGame(pId: string, username: string, gameSettings: any): Observable<boolean>{

        const bs = new BehaviorSubject<boolean>(false);
        console.log('Searching for game');
        const params = {"playerId": pId, "username": username, "numPlayerOptions": gameSettings};
        this.http.post<any>(ServerConfig.serverUrl + '/findGame', params).subscribe(gId => {
            this.gameId = gId;
            this.ws = new WebSocketApi(this);
            bs.next(true);
        });
        return bs.asObservable();
    }

    // if ready state it will pass data
    // if not ready state no other data is passed
    stompConnect(): Observable<boolean> {
        const bs = new BehaviorSubject<boolean>(false);
        this.ws.connect().subscribe(connectionStatus => {
            if(connectionStatus !== 'not connected'){
                bs.next(true);
            }
        });
        return bs.asObservable();
    }

    /**
     * should return intial hand for the player
     * @param pId - the player id
     * @param gameId - the game id
     */
    getHand(pId: string): Observable<InitGameData> {
        return this.http.get<InitGameData>(ServerConfig.serverUrl + '/getHand', {params: { playerId: pId, gameId: this.gameId}});
    }

    /*
     * The result only affects the caller and all the other clients get pushed an update from the subscription
     * @param action 
     */
    sendPlayerAction(action: SheepsheadAction): Observable<any> {
        return this.http.post(ServerConfig.serverUrl + '/gameAction', action);
    }

    sendBlindAction(action: SheepsheadAction): Observable<any> {
        return this.http.post(ServerConfig.serverUrl + '/gameBlindAction', action);
    }


    playerReady(): Observable<any>{
        return this.http.post<any>(ServerConfig.serverUrl + '/playerReady', this.gameId);
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

    getGameId(): string {
        return this.gameId;
    }

    setBlind(burriedCards:Card[]): void {
        this.bs.next(burriedCards);
    }

    getBlind(): Observable<any> {
        return this.bs.asObservable();
    }

    serializeCards(cards: Card[]): any[] {
        return cards.map((c: Card) => {
            return {suit: c.suit, value: c.value};
        });
    }

}

export interface CardAction extends SheepsheadAction {
    suit: string;
    value: string
}
/**
 * burriedCards will be null inthe case that is is a PassBlind
 */
export interface BlindAction extends SheepsheadAction{
    burriedCards: Card[]
}

export interface SheepsheadAction {
    action: String;
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



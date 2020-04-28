import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { ServerConfig } from '../services/server-config';
import { Observable, BehaviorSubject } from 'rxjs';
import { GameService } from '../services/game.service';

export class WebSocketApi {
    endpoint: string = '/ws';
    private stompClient: Stomp.Client;
    private gameInitTopic: string = '/topic/gameInit/';
    
    constructor(private gameService: GameService) { }
    /**
     * TODO: this should return a message saying everyone has connected to the game
     */
    connect(): Observable<any> {
        let subj: BehaviorSubject<any> = new BehaviorSubject<any>('not connected');
        console.log("Initializing WebSocket Connection");
        let ws = new SockJS(ServerConfig.serverUrl + this.endpoint);
        this.stompClient = Stomp.over(ws);   
        this.stompClient.connect({}, (frame) => {
            subj.next('connected');

        }, this.connectionError);
        return subj.asObservable();
    }


    /**
     * disconnects from the stompjs server if it is live
     */
    disconnect(): void {
        if(this.stompClient) {
            this.stompClient.disconnect((): any => {
                console.log('disconnected from stompjs');
            });
        }
    }

    /**
     * Gets called from @connect when the connection cuts out
     * @param error 
     */
    private connectionError(error: any): void {
        console.log('ERROR: ' + error);
        // try to reconnect
        setTimeout( () => {
            // this.connect();
        }, 5000);
    }


    /**
     * returns player hand and the opponentNames
     * @param playerId 
     */
    getInitGameInfo(playerId: string): Observable<any>{
        let bs: BehaviorSubject<any> = new BehaviorSubject<any>(null);
        if(this.stompClient){
            this.stompClient.subscribe(this.gameInitTopic + playerId, (data) => {
                console.log(data)
                bs.next(data.body);
            });
        }
        return bs.asObservable();
    }

}
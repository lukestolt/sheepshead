import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { ServerConfig } from '../services/server-config';
import { Observable, BehaviorSubject } from 'rxjs';
import { GameService } from '../services/game.service';

export class WebSocketApi {
    endpoint: string = '/ws';
    private stompClient: Stomp.Client;
    private gameDataTopic: string = 'topic/gameData'
    
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
     * when subbed, the value will be either null, not ready or ready
     */
    subToGameStatus(): Observable<any> {
        let subj: BehaviorSubject<any> = new BehaviorSubject<any>(null);
        const topic = "/topic/gamestatus/" + this.gameService.getGameId();
        console.log(topic);
        this.stompClient.subscribe(topic, (event) => {
            console.log(event)
            subj.next(JSON.parse(event.body));
            // this.handleMessage(event);
        });
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

    getGameInfo(): Observable<any>{
        let bs: BehaviorSubject<any> = new BehaviorSubject<any>(null);
        if(this.stompClient){
            this.stompClient.subscribe(this.gameDataTopic, (data) => {
                bs.next(data);
            });
        }
        return bs;
    }

}
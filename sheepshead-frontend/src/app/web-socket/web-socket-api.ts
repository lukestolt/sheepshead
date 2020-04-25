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
        let subj: BehaviorSubject<any> = new BehaviorSubject<any>(null);
        console.log("Initializing WebSocket Connection");
        let ws = new SockJS(ServerConfig.serverUrl + this.endpoint);
        this.stompClient = Stomp.over(ws);
        const t = this;
        const topic = "/topic/gamestatus/" + this.gameService.getGameId();
        this.stompClient.connect({}, (frame) => {
            t.stompClient.subscribe(topic, (event) => {
                console.log(event)
                // handle the event from the server
                if(event){
                    subj.next(event);
                }

                // this.handleMessage(event);
            });
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
            this.connect();
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
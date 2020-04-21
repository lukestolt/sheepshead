import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { ServerConfig } from '../services/server-config';
import { Observable, BehaviorSubject } from 'rxjs';

export class WebSocketApi {
    endpoint: string = '/ws';
    private stompClient: Stomp.Client;
    // TODO: this should be set to the path of the game api
    private topic: string = '/topic/game';
    
    /**
     * TODO: this should return a boolean when it is connected
     * connects to the stompjs server and handles the message
     */
    connect(): Observable<any> {
        let subj: BehaviorSubject<any> = new BehaviorSubject<any>(null);
        console.log("Initializing WebSocket Connection");
        let ws = new SockJS(ServerConfig.serverUrl + this.endpoint);
        this.stompClient = Stomp.over(ws);
        const t = this;
        this.stompClient.connect({}, (frame) => {
            // console.log('connected ' + frame);
            t.stompClient.subscribe(this.topic, (event) => {
                // handle the event from the server
                subj.next(event);
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

    /**
     * TODO: this could return boolean in the future
     * @param message to be sent to server during the game
     */
    send(data: any): void {
        const destination = '/app/gameaction';
        this.stompClient.send(destination,{}, data);
    }

    handleMessage(message: any): void {
        // console.log(message.body);

        // TODO: tell the game to handle the message after interpreting it
    }

}
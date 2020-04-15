import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

export class WebSocketApi {
    endpoint: string = '/portfolio';
    private stompClient: Stomp.Client;
    // TODO: this should be set to the path of the game api
    private topic: string = '/game/gameData';
    
    /**
     * TODO: this should return a boolean when it is connected
     * connects to the stompjs server and handles the message
     */
    connect(): void {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS('http://localhost:8080' + this.endpoint);
        this.stompClient = Stomp.over(ws);
        const t = this;
        this.stompClient.connect({}, (frame) => {
            console.log('connected ' + frame);
            t.stompClient.subscribe(this.topic, (event) => {
                // handle the event from the server
                this.handleMessage(event);
            });
        }, this.connectionError);
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
    send(action: any): void {
        // TODO: path the client is sending data
        const destination = '';
        // might needs to json it, not sure yet
        this.stompClient.send(destination,{}, action);
    }

    handleMessage(message: any): void {
        console.log(message);
        // TODO: tell the game to handle the message after interpreting it
    }

}
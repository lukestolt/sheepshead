import { Injectable } from '@angular/core';
import { over, Client } from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ServerConfig } from './server-config';
import { Observable, BehaviorSubject} from 'rxjs';
import { first, filter} from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class WebsocketConnectionService {
  private client: Client;
  private state: BehaviorSubject<SocketClientState>;

    constructor () {
      this.client = over(new SockJS(ServerConfig.serverUrl));
      this.state = new BehaviorSubject<SocketClientState>(SocketClientState.ATTEMPTING);
      // can put username and password in the headers
      this.client.connect({}, () => {
        this.state.next(SocketClientState.CONNECTED);
      });
    }

    ngOnDestroy() {
      this.connect().pipe(first()).subscribe(client => client.disconnect(null));
    }

    private connect(): Observable<any> {
      return new Observable<Client>(observer => {
        this.state.pipe(filter(state => state === SocketClientState.CONNECTED)).subscribe(() => {
          observer.next(this.client);         
        });
      });
    }
    
}

export enum SocketClientState {
  ATTEMPTING, CONNECTED
}

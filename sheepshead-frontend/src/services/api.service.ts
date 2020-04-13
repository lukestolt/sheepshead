import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerConfig } from './server-config'
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(  private http: HttpClient,) { }

  findGame(data: IFindGameParams): Observable<any> {
    return this.http.post(ServerConfig.serverUrl, data);
  }

  addPlayersToGame(name: string): Observable<any> {
    return this.http.get(ServerConfig.serverUrl);
  }

}

export interface IFindGameParams{
  numPlayers: number
}
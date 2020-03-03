import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { config } from './server-config'
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(  private http: HttpClient,) { }

  findGame(data: IFindGameParams): Observable<any> {
    return this.http.post(config.serverUrl, data);
  }
}

export interface IFindGameParams{
  numPlayers: number

}

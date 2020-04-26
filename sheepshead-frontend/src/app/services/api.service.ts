import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ServerConfig } from './server-config'
import { Observable } from 'rxjs';
import { PlayerDataService } from './player-data.service';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private playerService:PlayerDataService) {   }

  createPlayer(name: string): Observable<any> {
    return this.http.post<any>(ServerConfig.serverUrl + '/createUser', name);
  }
}

import { Injectable } from '@angular/core';
import { Player } from '../models/player';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

/**
 * keeps track of the player information so that it can be passed around to the other components
 * 
 */

export class PlayerDataService {

  private playerId: string;
  private name: string

  constructor(http: HttpClient){}

  init(playerId: string, name: string) {
    this.playerId = playerId;
    this.name = name;
    // tell the server that this person in logged in? 
  }


  getPlayerId(): string {
    return this.playerId;
  }

  getPlayerName(): string {
    return this.name;
  }

}

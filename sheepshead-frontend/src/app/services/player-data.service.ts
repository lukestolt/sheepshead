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

  player: Player;
  constructor(http: HttpClient){}

  init(playerId: string, name: string) {
    if(!this.player){
      this.player = new Player(playerId, name);
    }
    // tell the server that this person in logged in? 
  }

  isInit():boolean {

    return (this.player != null && (this.player.id != null && this.player.name != null));
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from '../services/game.service';
import { PlayerDataService } from '../services/player-data.service';

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {

  private numPlayersSelected: string[] = ["3"];

  constructor(private _router:Router, private gameService: GameService, private pds: PlayerDataService) { }

  ngOnInit() {
  }

  /**
   * navigate to the game component where the searching actaully happens
   */
  private searchForGame(): void {
    this.gameService.findGame(this.pds.getPlayerId(), this.numPlayersSelected);
    this._router.navigateByUrl('/game');
  }

  private playerNumClick(event: any, num: string): void {
    console.log(event);
    if(!this.numPlayersSelected.includes(num)) {
      this.numPlayersSelected.push(num);
    }

  }
}

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

  numPlayersSelected: string[] = ["3"];

  constructor(private _router:Router, private gameService: GameService, private pds: PlayerDataService) {
   }

  ngOnInit() {
  }

  /**
   * navigate to the game component where the searching actaully happens
   */
  searchForGame(): void {
    this.gameService.findGame(this.pds.player.id, this.pds.player.name, this.numPlayersSelected).subscribe(res => {
      
      if(res == true) {
        // stompConnect returns true or false
        this.gameService.stompConnect().subscribe((status) => {
          if(status == true){
              this._router.navigateByUrl('/game');
          }
        });       
      }
    });
  }

  playerNumClick(event: any, num: string): void {
    if(!this.numPlayersSelected.includes(num)) {
      this.numPlayersSelected.push(num);
    }
  }
}

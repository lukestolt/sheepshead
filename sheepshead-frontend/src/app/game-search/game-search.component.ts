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

  constructor(private _router:Router, private gameService: GameService, private pds: PlayerDataService) {
   }

  ngOnInit() {
  }

  /**
   * navigate to the game component where the searching actaully happens
   */
  private searchForGame(): void {
    this.gameService.findGame(this.pds.player.id, this.numPlayersSelected).subscribe(res => {
      
      if(res == true) {
        console.log('found game');
        // stompConnect returns true or false
        this.gameService.stompConnect().subscribe((status) => {
          if(status == true){
            console.log('stomp Connected')
            //sub to the gamestatus
            this.gameService.ws.subToGameStatus().subscribe(status => {
              console.log(status);
              if(status === 'ready'){
                this._router.navigateByUrl('/game');
              }
            });
            // tell the server that it has connected and that player is ready
            this.gameService.playerReady().subscribe(() => {console.log('told server player was ready'); });
          }
        });       
      }
    });
    
  }

  private playerNumClick(event: any, num: string): void {
    console.log(event);
    if(!this.numPlayersSelected.includes(num)) {
      this.numPlayersSelected.push(num);
    }

  }
}

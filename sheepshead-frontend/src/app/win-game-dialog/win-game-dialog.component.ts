import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { WinGameResponse } from '../game/game.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-win-game-dialog',
  templateUrl: './win-game-dialog.component.html',
  styleUrls: ['./win-game-dialog.component.css']
})
export class WinGameDialogComponent {
  names: string[];
  points: number[];
  winnerName: string;
  // team 1 has the picker
  team1: string;
  team2: string[];

  team1P1Display:string;
  team2P1Display:string;
  team2P2Display:string;

  constructor(public dialogRef: MatDialogRef<WinGameDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router) {
      this.winnerName = data.winnerName;
      this.names = data.names;
      this.points = data.points;
      this.team1 = data.team1;
      this.team2 = data.team2;
      this.generateTeamsData()
    }

    onCloseClick(): void {
      this.dialogRef.close();
    }

    getWinnerText(): string{
      if(this.winnerName === this.team1 ){
        return "Team 1 is the Winner";
      }
      return "Team 2 is the Winner";
    }
    generateTeamsData():void{
      this.team1P1Display = this.team1 + ": ";
      let points;
      this.team1P1Display += this.getPoints(this.team1);
      let t2p1 = this.team2[0];
      this.team2P1Display = t2p1 + ": " + this.getPoints(t2p1);
      let t2p2 = this.team2[1];
      this.team2P2Display = t2p2 + ": " + this.getPoints(t2p2);

    }

    getPoints(playerName: string) {
      for(let x = 0; x < this.names.length; x++) {
        if(this.names[x] === playerName){
          return this.points[x];
        }
      }
    }

}

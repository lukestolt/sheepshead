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

  constructor(public dialogRef: MatDialogRef<WinGameDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private router: Router) {
      this.winnerName = data.winnerName;
      this.names = data.names;
      this.points = data.points;
    }

    onCloseClick(): void {
      this.dialogRef.close();
    }

}

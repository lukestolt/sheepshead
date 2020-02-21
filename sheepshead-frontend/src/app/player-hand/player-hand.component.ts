import { Component, OnInit } from '@angular/core';
import * as PIXI from 'pixi.js';

@Component({
  selector: 'app-player-hand',
  templateUrl: './player-hand.component.html',
  styleUrls: ['./player-hand.component.css']
})
export class PlayerHand implements OnInit {
  private numCards: number;
  
  constructor() { }

  ngOnInit() {
  }

  public getContainer(): PIXI.Container {

    return null;
  }

}

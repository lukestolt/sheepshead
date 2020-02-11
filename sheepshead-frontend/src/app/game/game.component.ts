import { Component, OnInit } from '@angular/core';
import * as PIXI from 'pixi.js';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  createCards() {
    const app = new PIXI.Application({width: 250, height: 250});
    document.body.appendChild(app.view);

    // TODO: draw the cards here
  }

}

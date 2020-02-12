import { Component, OnInit } from '@angular/core';
import * as PIXI from 'pixi.js';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  public readonly renderer: PIXI.Renderer;

  constructor() {
    this.renderer = new PIXI.Renderer({width: 732, height: 412});
    this.initGame();
    this.doRender(new PIXI.Container());
  }


  ngOnInit() {
  }

  createCards() {
    // TODO: draw the cards here
  }
  private doRender(stage: PIXI.Container): void {
    // this.renderer.render(stage);
    this.renderer.render(stage);
  }

  private initGame(): void {
    const green =  0x0abb18;
    this.renderer.backgroundColor = green;
    // document.body.appendChild(this.renderer.view);
  }

  addToScreen(): void {
    document.body.appendChild(this.renderer.view);
  }
}

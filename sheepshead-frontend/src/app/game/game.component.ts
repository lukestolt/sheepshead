import { Component, OnInit } from '@angular/core';
import * as PIXI from 'pixi.js';
import { PlayerHand } from '../player-hand/player-hand.component';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  public readonly renderer: PIXI.Renderer;
  public app: PIXI.Application;
  public sprites: any[];
  constructor() {
    this.initGame();
  }


  ngOnInit() {
  }

  private initGame(): void {
  
    this.app = new PIXI.Application({width: 250, height: 250});
    
    const green =  0x0abb18;
    this.app.renderer.backgroundColor = green;
    this.app.renderer.view.style.position = 'absolute';
    this.app.renderer.view.style.display = 'block';
    this.app.renderer.autoDensity = true;
    this.app.renderer.resize(window.innerWidth, window.innerHeight);
    this.addToScreen();

    const texture = PIXI.Texture.from('../../assets/cards/2_of_clubs.png');
    const sprite = new PIXI.Sprite(texture);
    this.app.stage.addChild(sprite);

  }

  private addToScreen(): void {
    document.body.appendChild(this.app.view);
  }

  private renderGame(container: PIXI.Container): void {
    // const renderer = PIXI.autoDetectRenderer();
    // renderer.render(container);
    this.app.stage.addChild(container);
  }


  // this is an example method that is no longer used
  drawCards(): void {
    const stage: PIXI.Container = new PIXI.Container();
    const graphics: PIXI.Graphics = new PIXI.Graphics();
    const black = 0x000000;
    const red = 0xff0000;
    const white = 0xffffff;
    graphics.beginFill(white);
    graphics.drawRect(100, 100, 200, 300);
    graphics.endFill();
    // card format: card# and right below is the shape
    const text = new PIXI.Text('5', {fontFamily: 'italic', fontSize: 24, fill: red});
    text.position.set(110 , 110);
    graphics.addChild(text);
    graphics.beginFill(0xff0000);
    graphics.drawPolygon([100, 100, 110, 110, 100, 120, 90, 110]);
    graphics.endFill();

    stage.addChild(graphics);
    this.renderGame(stage);

  }

  private drawHand(): void {
    const hand = new PlayerHand();
    //this.renderGame(hand.getStage())
  }

}

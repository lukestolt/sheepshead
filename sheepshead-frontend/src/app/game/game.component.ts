import { Component, OnInit } from '@angular/core';
import * as PIXI from 'pixi.js';

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

    // this.initGame();
    // this.renderer = new PIXI.Renderer({width: 732, height: 412});
    // this.initGame();
    // this.doRender(new PIXI.Container());
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

    const texture = PIXI.Texture.from('../../assets/characters.png');
    const sprite = new PIXI.Sprite(texture);
    this.app.stage.addChild(sprite);

  }

  private addToScreen(): void {
    document.body.appendChild(this.app.view);
  }

}

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

  constructor() {

    this.initGame();
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
  }

  private addToScreen(): void {
    document.body.appendChild(this.app.view);
  }

  addSprite(): void {
    const loader = this.app.loader;
    loader.add('../../assets/characters.png')
          .load(this.spriteSetup);
  }

  spriteSetup(): void {
    const characters = new PIXI.Sprite(this.app.loader.resources['../../assets/characters.png'].texture);
    this.app.stage.addChild(characters);
  }
}

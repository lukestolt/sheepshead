import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {

  constructor(private _router:Router) { }

  ngOnInit() {
  }

  /**
   * navigate to the game component where the searching actaully happens
   */
  private searchForGame(): void {
    this._router.navigateByUrl('/game');
  }
}

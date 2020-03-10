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

  searchForGame(): void {
    this._router.navigateByUrl('/game');
  }
}

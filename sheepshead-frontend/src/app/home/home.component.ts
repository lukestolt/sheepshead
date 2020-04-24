import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PlayerDataService } from '../services/player-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private _router: Router, private pds: PlayerDataService) { 
    this.pds.init('rand-playerID', 'Luke');
  }

  ngOnInit() {
  }

}

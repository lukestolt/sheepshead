import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PlayerDataService } from '../services/player-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private formName: string = '';

  constructor(private _router: Router, private pds: PlayerDataService) { 

  }

  ngOnInit() {
  }

  findGameClick(): void {
    if(this.formName && this.formName !== '')  {

      //TODO: generate randplayer id and send it to server?
      this.pds.init('rand-playerID', this.formName);
      this._router.navigateByUrl('/gamesearch');
    } else {
      alert('Enter a valid name');
    }
    
  }


}

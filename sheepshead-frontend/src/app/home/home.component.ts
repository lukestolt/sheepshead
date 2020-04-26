import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import { PlayerDataService } from '../services/player-data.service';
import { pbkdf2Sync } from 'crypto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private formName: string = '';

  constructor(private _router: Router, private apiService: ApiService, private pds: PlayerDataService) { 

  }

  ngOnInit() {
  }

  findGameClick(): void {
    if(this.formName && this.formName !== '')  {
      this.apiService.createPlayer(this.formName).subscribe(player => {
        this.pds.init(player.uuid, this.formName);
        this._router.navigateByUrl('/gamesearch');
      });
    } else {
      alert('Enter a valid name');
    }
  }
}

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { GameService } from './services/game.service';
import { PlayerDataService } from './services/player-data.service';

@Injectable({
  providedIn: 'root'
})
export class GameGuard implements CanActivate {

  constructor(private gameService: GameService, private router: Router, private pds: PlayerDataService) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if(!this.pds.isInit()){
        this.router.navigateByUrl('/home');
        return false;
      }
      if(this.gameService.getGameId() == null){
        this.router.navigateByUrl('/gamesearch');
        return false;
      }
      return true;
  }
  
}

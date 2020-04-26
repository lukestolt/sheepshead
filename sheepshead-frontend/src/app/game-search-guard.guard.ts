import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PlayerDataService } from './services/player-data.service';

@Injectable({
  providedIn: 'root'
})
export class GameSearchGuard implements CanActivate {
  constructor(private pds: PlayerDataService, private router: Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(!this.pds.isInit()){
      this.router.navigateByUrl('/home');
      return false;
    }
    return true;
  }
  
}

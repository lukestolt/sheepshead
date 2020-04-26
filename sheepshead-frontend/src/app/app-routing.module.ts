import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { GameSearchComponent } from './game-search/game-search.component';
import { GameComponent } from './game/game.component';
import { GameSearchGuard } from './game-search-guard.guard';
import { GameGuard } from './game.guard';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', component: HomeComponent },
  { path: 'gamesearch', component: GameSearchComponent, canActivate: [GameSearchGuard]},
  { path: 'game', component: GameComponent, canActivate: [GameGuard]},
  { path: '**', component: HomeComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
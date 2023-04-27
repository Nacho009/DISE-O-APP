import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GamesService } from '../games.service';
import { SharedDataService } from '../shared-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    private router: Router, private gameService: GamesService, public sharedDataService: SharedDataService
   
  ) {}
  

  requestGame(juego: any){

    console.log(this.gameService.requestGame(juego, this.sharedDataService.username
      ));
  }
}

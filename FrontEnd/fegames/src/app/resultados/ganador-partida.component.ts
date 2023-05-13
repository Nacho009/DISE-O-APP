import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedDataService } from '../shared-data.service';
import { WebSocketService } from '../websocket.service';

@Component({
  selector: 'app-ganador-partida',
  templateUrl: './ganador-partida.component.html',
  styleUrls: ['./ganador-partida.component.css']
})
export class GanadorPartidaComponent {
  ganador: String;

  constructor(private router: Router,     private sharedDataService: SharedDataService   , private websocketService: WebSocketService
    ) {
    this.ganador = this.sharedDataService.ganador;

    if(this.sharedDataService.username===this.ganador){
      this.websocketService.connect('ws://localhost:80/wsGames').subscribe();

      this.websocketService.sendGanador(this.sharedDataService.ganador)  
    }

  }

  volverAlMenu() {
    this.sharedDataService.match.pop
    this.router.navigate(['/home']); 
  }
}


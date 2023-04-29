import { Component,  OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { GamesService } from '../games.service';
import { SharedDataService } from '../shared-data.service';
import { ToastrService } from 'ngx-toastr';
import { WebSocketService } from '../websocket.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{

  private socket$!: Subject<any>;

  constructor(
    private router: Router, private gameService: GamesService, private sharedDataService: SharedDataService, 
    private toastr: ToastrService, private websocketService: WebSocketService
   
  ) {
    const url = 'ws://localhost:80/wsGames';
    this.socket$ = this.websocketService.connect(url);

    this.socket$.subscribe({
      next: (response: any) => {
        console.log("WebSocket con", response);
      },
      error: (error: any) => {
        console.error('WebSocket error:', error);

      }
    });
  }
  
  // ngOnInit() {
  //   const url = 'ws://localhost:80/wsGames';
  //   this.socket$ = this.websocketService.connect(url);

  //   this.socket$.subscribe({
  //     next: (response: any) => {
  //       console.log("WebSocket con", response);
  //     },
  //     error: (error: any) => {
  //       console.error('WebSocket error:', error);

  //     }
  //   });
  // }
  ngOnDestroy() {
    this.websocketService.disconnect();
  }

  requestGame(juego: String){

    if(!this.sharedDataService.match.includes(this.sharedDataService.username)){

      this.gameService.requestGame('nm', this.sharedDataService.username).subscribe({
        next: (response: any) => {
          console.log(response);
          this.toastr.success('Juego solicitado correctamente', 'Éxito');

          this.websocketService.sendBroadcast("HOLALLALAL");
        },
        error: (error: any) => {
          console.error('Error en la solicitud:', error);
          this.toastr.error('No se pudo solicitar el juego', 'Error');

        }
      });

    }else{
      this.toastr.warning('Ya estás dentro del juego', 'Advertencia');
    }
    
  
  }
  

  
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { GamesService } from '../games.service';
import { SharedDataService } from '../shared-data.service';
import { ToastrService } from 'ngx-toastr';
import { WebSocketService } from '../websocket.service';
import { Subject } from 'rxjs';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  jugadores!: number;
  private websocketUrl = 'ws://localhost:80/wsGames';

  constructor(
    private router: Router, private gameService: GamesService, private sharedDataService: SharedDataService,
    private toastr: ToastrService, private websocketService: WebSocketService

  ) {
    this.jugadores = 0;
  }

  ngOnInit() {
    // Conectar al WebSocket
    this.websocketService.connect(this.websocketUrl).subscribe();

    this.websocketService.socket$.subscribe((messageEvent: MessageEvent) => {
      console.log('WebSocket message received:', messageEvent.data);

      const messageData = JSON.parse(messageEvent.data);
      if (messageData.hasOwnProperty('ready') && messageData.ready === true) {
        this.sharedDataService.id= messageData.id;
        this.router.navigate(['/juego']);
      }
    });
  }
  ngOnDestroy() {
    this.websocketService.disconnect();
  }

  async requestGame(juego: String) {
    if (!this.sharedDataService.match.includes(this.sharedDataService.username)) {
      try {
        const response: any = await firstValueFrom(
          this.gameService.requestGame('nm', this.sharedDataService.username)
        );
        console.log(response);
        this.toastr.success('Juego solicitado correctamente', 'Éxito');
        this.jugadores = response.players.length;
        if (this.jugadores == 2) {
          await this.websocketService.sendBroadcast(JSON.stringify(response));
        }
      } catch (error) {
        console.error('Error en la solicitud:', error);
        this.toastr.error('No se pudo solicitar el juego', 'Error');
      }
    } else {
      this.toastr.warning('Ya estás dentro del juego', 'Advertencia');
    }
  }
}
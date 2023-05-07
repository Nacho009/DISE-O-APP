import { Component, OnInit  } from '@angular/core';
import { GamesService } from '../games.service';
import { SharedDataService } from '../shared-data.service';
import { Router } from '@angular/router';
import { WebSocketService } from '../websocket.service';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css']
})
export class JuegoComponent implements OnInit{

  byteArray: number[][] = [];


  constructor(private websocketService: WebSocketService, private router: Router, private gameService: GamesService, private sharedDataService: SharedDataService) {

  }

  ngOnInit(): void {

    this.gameService.getByteArray(this.sharedDataService.username).subscribe(integerList => {
      this.byteArray = integerList;
    });

    this.websocketService.connect("ws://localhost:80/wsGames").subscribe();

    this.websocketService.socket$.subscribe((messageEvent: MessageEvent) => {
      console.log('WebSocket message received:', messageEvent.data);

      const messageData = JSON.parse(messageEvent.data);
      // if (messageData.hasOwnProperty('ready') && messageData.ready === true) {
      //   this.sharedDataService.type = 'BROADCAST';
      // }
      switch (this.sharedDataService.type) {
        case 'MOVEMENT':

          break;
        case 'CHAT':

          break;
        case 'BROADCAST':

          break;
        default:
          console.warn('Unrecognized message type:', messageData.type);
      }
    });

  }

  // copyMatrix(matrix: number[][]): number[][] {
  //   const copiedMatrix: number[][] = [];

  //   for (const row of matrix) {
  //     copiedMatrix.push([...row]);
  //   }

  //   return copiedMatrix;
  // }
  
  onCellClick(rowIndex: number, cellIndex: number): void {
    console.log(`Celda seleccionada: Fila ${rowIndex}, columna ${cellIndex}`);
    // LOGICA Y CONECTAR BACK
  }

  onAddDigitsClick(): void {
    console.log('Agregar dígitos');
    // CONECTAR BACK
  }

}

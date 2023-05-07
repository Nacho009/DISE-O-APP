import { Component, OnInit } from '@angular/core';
import { GamesService } from '../games.service';
import { SharedDataService } from '../shared-data.service';
import { Router } from '@angular/router';
import { WebSocketService } from '../websocket.service';
import { Move } from '../Clases/Move';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css']
})
export class JuegoComponent implements OnInit {

  byteArray: number[][] = [];
  chatMessages: Array<{ author: string, text: string }> = []; // Añadir variable para almacenar los mensajes del chat
  newMessage: string = ''; // Añadir variable para almacenar el mensaje que el usuario está escribiendo
  movimientos!: number
  apto!: number
  row1!: number
  col1!: number


  constructor(private websocketService: WebSocketService, private router: Router, private gameService: GamesService, private sharedDataService: SharedDataService) {
    this.movimientos=0;
    this.apto=0;
  }

  ngOnInit(): void {

    console.log(this.sharedDataService.id);
    this.gameService.getByteArray(this.sharedDataService.username).subscribe(integerList => {
      this.byteArray = integerList;
    });

    this.websocketService.connect("ws://localhost:80/wsGames").subscribe();

    this.websocketService.socket$.subscribe((messageEvent: MessageEvent) => {
      console.log('WebSocket message received:', messageEvent.data);
      console.log(messageEvent)

      const messageData = JSON.parse(messageEvent.data);
      

      switch (this.sharedDataService.type) {
        case 'MOVEMENT':

          break;
        case 'CHAT':
          // Añadir la lógica para manejar el mensaje de chat recibido
          this.chatMessages.push({ author: messageData.author, text: messageData.text });
          break;
        case 'BROADCAST':

          break;
        default:
          // console.warn('Unrecognized message type:', messageData.type);
      }
    });

  }

  onCellClick(rowIndex: number, cellIndex: number): void {
    console.log(`Celda seleccionada: Fila ${rowIndex}, columna ${cellIndex}`);
    this.apto++;
    if(this.apto==2){
      this.apto=0;
      const move: Move = new Move(this.movimientos, this.sharedDataService.id, this.sharedDataService.username,this.row1, this.col1, rowIndex, cellIndex);
      console.log(move);
      this.websocketService.sendMovement(this.sharedDataService.id, move);
    }else{
      this.row1=rowIndex;
      this.col1=cellIndex;
    }
    // LOGICA Y CONECTAR BACK
  }

  onAddDigitsClick(): void {
    console.log('Agregar dígitos');
    // CONECTAR BACK
  }

  onSendMessageClick(): void {
    console.log('Enviar mensaje:', this.newMessage);
    // Lógica para enviar el mensaje de chat a través del WebSocket
    this.websocketService.sendChat('all', this.newMessage); // 'all' es un ejemplo de cómo indicar un mensaje global a todos los usuarios
    // Vaciar el campo de entrada después de enviar el mensaje
    this.newMessage = '';
  }

}

import { Component, OnInit } from '@angular/core';
import { GamesService } from '../games.service';
import { SharedDataService } from '../shared-data.service';
import { Router } from '@angular/router';
import { WebSocketService } from '../websocket.service';
import { Move } from '../Clases/Move';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css'],
})
export class JuegoComponent implements OnInit {
  byteArray: number[][] = [];
  chatMessages: Array<{ author: string; text: string }> = []; 
  newMessage: string = '';
  movimientos!: number;
  apto!: number;
  row1!: number;
  col1!: number;
  vale: Boolean;


  constructor(
    private websocketService: WebSocketService,
    private toastr: ToastrService,
    private router: Router,
    private gameService: GamesService,
    private sharedDataService: SharedDataService
  ) {
    this.movimientos = 0;
    this.apto = 0;
    this.vale=false;
  }

  ngOnInit(): void {
    this.gameService
      .getByteArray(this.sharedDataService.username)
      .subscribe((integerList) => {
        this.byteArray = integerList;
      });

    this.websocketService.connect('ws://localhost:80/wsGames').subscribe();

    this.websocketService.socket$.subscribe((messageEvent: MessageEvent) => {
      // console.log('WebSocket message received:', messageEvent.data);
      // console.log(messageEvent);

      const messageData = JSON.parse(messageEvent.data);

      switch (messageData.type) {
        case 'MOVEMENT':
          this.gameService
            .getByteArray(this.sharedDataService.username)
            .subscribe((integerList) => {
              this.byteArray = integerList;
            });

          break;
        case 'CHAT':
          // Añadir la lógica para manejar el mensaje de chat recibido
          this.chatMessages.push({
            author: messageData.author,
            text: messageData.text,
          });
          break;
        case 'FIN':

        this.router.navigate(['/ganador']); 

        break;
        default:
        // console.warn('Unrecognized message type:', messageData.type);
      }
    });
  }

  valido(move: Move) {
    // Verifica que los índices estén dentro del rango del tablero.
    if (
      !this.indicesDentroDelRango(move.row1, move.col1) ||
      !this.indicesDentroDelRango(move.row2, move.col2)
    ) {
      this.vale = false;
    }else{
      this.vale = true;
    }

    // Verifica que las celdas no sean las mismas.
    if (move.row1 === move.row2 && move.col1 === move.col2) {
      console.log("SON LAS MISMAS")
      this.vale = false;
    }else{
      console.log("SON LAS MISMASssss")
      this.vale = true;
    }

    // Verifica que los valores en las celdas sean iguales.
// Verifica que los valores en las celdas sean iguales o no sumen 10.
    if ((this.byteArray[move.row1][move.col1] !== this.byteArray[move.row2][move.col2])) {
      console.log("NO CUMPLEN LA CONDICIÓN");
      this.vale = false;
      if(((this.byteArray[move.row2][move.col2]) + (this.byteArray[move.row1][move.col1])) === 10){
        this.vale = true;
      }
      if (!this.esAdyacente(move) && !this.numerosIntermediosSonCero(move) && !this.esMovimientoEspecial(move) ) {
        console.log("SE QUEDA AQUI")
        this.vale = false;
      }
    }else{
      this.vale = true;
      if (move.row1 === move.row2 && move.col1 === move.col2) {
        console.log("SON LAS MISMAS")
        this.vale = false;
      }
      if (!this.esAdyacente(move) && !this.numerosIntermediosSonCero(move) && !this.esMovimientoEspecial(move)) {
        console.log("SE QUEDA AQUI")
        this.vale = false;
      }
    }

    // Si todas las condiciones se cumplen, el movimiento es válido.
    return this.vale;
  }

  sumaDiez(move: Move) {
    return (this.byteArray[move.row1][move.col1] + this.byteArray[move.row2][move.col2]) === 10;
  }
  indicesDentroDelRango(row: number, col: number) {
    const size = this.byteArray.length;
    return row >= 0 && row < size && col >= 0 && col < size;
  }

  esAdyacente(move: Move) {
    return (
      Math.abs(move.row1 - move.row2) <= 1 &&
      Math.abs(move.col1 - move.col2) <= 1
    );
  }

  esMovimientoEspecial(move: Move) {
    return move.col1 === 0 && move.col2 === this.byteArray.length - 1;
  }

  numerosIntermediosSonCero(move: Move) {
    const rowDiff = move.row2 - move.row1;
    const colDiff = move.col2 - move.col1;

    // Verifica movimientos horizontales
    if (move.row1 === move.row2) {
      for (
        let col = Math.min(move.col1, move.col2) + 1;
        col < Math.max(move.col1, move.col2);
        col++
      ) {
        if (this.byteArray[move.row1][col] !== 0) {
          return false;
        }
      }
    }
    // Verifica movimientos verticales
    else if (move.col1 === move.col2) {
      for (
        let row = Math.min(move.row1, move.row2) + 1;
        row < Math.max(move.row1, move.row2);
        row++
      ) {
        if (this.byteArray[row][move.col1] !== 0) {
          return false;
        }
      }
    }
    // Verifica movimientos diagonales
    else if (Math.abs(rowDiff) === Math.abs(colDiff)) {
      const rowStep = rowDiff > 0 ? 1 : -1;
      const colStep = colDiff > 0 ? 1 : -1;
      let row = move.row1 + rowStep;
      let col = move.col1 + colStep;
      while (row !== move.row2 && col !== move.col2) {
        if (this.byteArray[row][col] !== 0) {
          return false;
        }
        row += rowStep;
        col += colStep;
      }
    } else {
      // No es un movimiento horizontal, vertical ni diagonal válido
      return false;
    }
    return true;
  }
  onCellClick(rowIndex: number, cellIndex: number): void {
    console.log(`Celda seleccionada: Fila ${rowIndex}, columna ${cellIndex}`);
    this.apto++;
    if (this.apto == 2) {
      this.apto = 0;
      const move: Move = new Move(
        this.movimientos,
        this.sharedDataService.id,
        this.sharedDataService.username,
        this.row1,
        this.col1,
        rowIndex,
        cellIndex
      );
      this.movimientos++;
      if (this.valido(move)) {
        this.websocketService.sendMovement(this.sharedDataService.id, move);
      } else {
        this.toastr.warning(
          'Movimiento no valido, prueba de nuevo...',
          'Advertencia'
        );
      }
    } else {
      this.row1 = rowIndex;
      this.col1 = cellIndex;
    }
  }

  onAddDigitsClick(): void {
    console.log('Agregar dígitos');

    this.gameService
    .getDigits(this.sharedDataService.username)
    .subscribe((integerList) => {
      this.byteArray = integerList;
    });

  }

  onSendMessageClick(): void {
    console.log('Enviar mensaje:', this.newMessage);
    // Lógica para enviar el mensaje de chat a través del WebSocket
    this.websocketService.sendChat('all', this.newMessage); // 'all' es un ejemplo de cómo indicar un mensaje global a todos los usuarios
    // Vaciar el campo de entrada después de enviar el mensaje
    this.newMessage = '';
  }
}

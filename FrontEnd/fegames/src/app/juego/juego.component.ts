import { Component, OnInit  } from '@angular/core';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css']
})
export class JuegoComponent implements OnInit{

  board: any; 

  constructor() {
    this.board = {
      digits: [
        [1, 2, 3, 4, 5, 6, 7, 8, 1],
        [2, 3, 4, 5, 6, 7, 8, 9, 1],
        [3, 4, 5, 6, 7, 8, 9, 1, 2],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0]
      ]
    };
  }

  ngOnInit(): void {}

  onCellClick(rowIndex: number, cellIndex: number): void {
    console.log(`Celda seleccionada: Fila ${rowIndex}, columna ${cellIndex}`);
    // LOGICO Y CONECTAR BACK
  }

  onAddDigitsClick(): void {
    console.log('Agregar dígitos');
    // CONECTAR BACK
  }

}

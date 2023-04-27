import { Component, OnInit  } from '@angular/core';
import { GamesService } from '../games.service';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css']
})
export class JuegoComponent implements OnInit{

  byteArrayP1: number[][] = [];
  byteArrayP2: number[][] = [];

  board: any; 

  constructor(private gameService: GamesService) {

  }

  ngOnInit(): void {

    this.gameService.getByteArray().subscribe(integerList => {
      this.byteArrayP1 = integerList;
      this.byteArrayP2=integerList;
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

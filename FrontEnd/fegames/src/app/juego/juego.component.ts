import { Component, OnInit  } from '@angular/core';
import { GamesService } from '../games.service';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css']
})
export class JuegoComponent implements OnInit{

  byteArray: number[][] = [];
  board: any; 

  constructor(private gameService: GamesService) {

  }

  ngOnInit(): void {

    this.gameService.getByteArray().subscribe(integerList => {
      this.byteArray = integerList;
    });

  }

  onCellClick(rowIndex: number, cellIndex: number): void {
    console.log(`Celda seleccionada: Fila ${rowIndex}, columna ${cellIndex}`);
    // LOGICA Y CONECTAR BACK
  }

  onAddDigitsClick(): void {
    console.log('Agregar dígitos');
    // CONECTAR BACK
  }

}

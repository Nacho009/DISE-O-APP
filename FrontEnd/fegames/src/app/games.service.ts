import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JuegoComponent } from './juego/juego.component';

@Injectable({
  providedIn: 'root'
})
export class GamesService {
  httpOptions: { headers: any; };
  board!: JuegoComponent;

  constructor(private httpClient : HttpClient) { 

    
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

  }

  requestGame(){

  }

  initBoard(): void {
    this.httpClient.get("http://localhost:80/games/requestBoard", this.httpOptions)
      .subscribe({
        next: (response: any) => {
          console.log('Estado HTTP:', response.status);
          this.board=response;
        },
        error: (error: any) => {
          console.error('Error en la solicitud:', error);
          alert('Hubo un error al cargar el tablero');
        }
      });
  }

}

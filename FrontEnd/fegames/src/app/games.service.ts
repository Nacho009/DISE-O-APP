import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JuegoComponent } from './juego/juego.component';
import { Observable } from 'rxjs';

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

  getByteArray(): Observable<number[][]> {
    return this.httpClient.get<number[][]>("http://localhost:80/games/requestBoard");
  }

}

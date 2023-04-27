import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JuegoComponent } from './juego/juego.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GamesService {
  private httpOptions;

  constructor(private httpClient : HttpClient) { 

    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

  }

  requestGame(juego: any, player: any){
    return this.httpClient.get("http://localhost:80/games/requestGame?juego="+juego+"&player="+player+"")
    
  }

  getByteArray(): Observable<number[][]> {
    return this.httpClient.get<number[][]>("http://localhost:80/games/requestBoard");
  }

}

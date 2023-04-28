import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JuegoComponent } from './juego/juego.component';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
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

  requestGame(juego: String, player: String): Observable<String> {
    const url = `http://localhost:80/games/requestGame?juego=${juego}&player=${player}`;
    console.log(url)
    return this.httpClient.get<String>(url)
    
  }

  getByteArray(): Observable<number[][]> {
    return this.httpClient.get<number[][]>("http://localhost:80/games/requestBoard");
  }

}

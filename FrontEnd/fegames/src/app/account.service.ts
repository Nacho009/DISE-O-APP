import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private httpOptions;

  constructor(private httpClient : HttpClient) {

    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

   }


   register(info: any) {
    this.httpClient.post("http://localhost:80/users/register", info, this.httpOptions)
    .subscribe(
      respuesta => {
        alert(respuesta);    
        console.log(respuesta);
      },
      error => {
        console.error(error);
      }
    );
  }
  

  login(info: any) : Observable <any> {
    return this.httpClient.put("http://localhost:80/users/login", info, this.httpOptions)
    
  }



}

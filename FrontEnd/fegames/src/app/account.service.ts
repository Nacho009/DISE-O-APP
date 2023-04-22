import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private httpOptions;

  constructor(private router: Router, private httpClient : HttpClient) {

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
  


  login(info: any): void {
    this.httpClient.put("http://localhost:80/users/login", info, this.httpOptions)
      .subscribe({
        next: (response: any) => {
          console.log('Estado HTTP:', response.status);
          this.router.navigate(['/home']);
        },
        error: (error: any) => {
          console.error('Error en la solicitud:', error);
          alert('Credenciales incorrectas');
        }
      });
  }

  prueba(): void {
          this.router.navigate(['/prueba']);
  }
  
}

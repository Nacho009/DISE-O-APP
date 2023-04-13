import { Component } from '@angular/core';
import { AccountService } from '../account.service';
import { GamesService } from '../games.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  name? : string;
  pwd1? : string;
  message? : string
  loginCorrecto : boolean = false

  constructor(private accountService : AccountService,
    private gamesService : GamesService){
    
  }
  ngOnInit(): void{

  }

  login(){
    let info = {
      name : this.name,
      pwd1 : this.pwd1
    }

    this.accountService.login(info).subscribe(
      respuesta => {
        this.message = "hola, " + this.name
        this.loginCorrecto = true
      },
      error => {
        this.message = "ha habido un error"
        this.loginCorrecto = false

      }
    )
  }

  requestGame(){
      this.gamesService.requestGame()
  }
}

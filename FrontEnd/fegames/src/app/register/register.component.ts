import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
//para decir que no tienen valor definido-> ?
    name : string = "Pepe"
    email : string
    pwd1? : string
    pwd2? : string

    constructor(private accountService : AccountService){
      this.email = "pepe@pepe.com"
    }
    ngOnInit(): void{

    }

    register(){
      //var todo el fichero
      //let dentro de este metodo
       let info = {
        name : this.name,
        email : this.email,
        pwd1 : this.pwd1,
        pwd2 : this.pwd2
       } 
       this.accountService.register(info);


    }
}

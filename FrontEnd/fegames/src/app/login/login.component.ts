import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  getData!: boolean;
  model: any = {};
 

  constructor(
    private router: Router, private userService: AccountService
   
  ) {}

  ngOnInit() {
  }

  loginUser() {
   

    const loginData = {
      name: this.model.username,
      pwd: this.model.password
    };

    this.userService.login(JSON.stringify(loginData));

    console.log(JSON.stringify(loginData));

     
    
}
}

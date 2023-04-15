import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

  model: any = {};

  constructor(
    private router: Router, private userService: AccountService
   
  ) {}

  ngOnInit() {
  }


  registerUser(){

    const registerData = {
      name: this.model.username,
      email: this.model.email,
      pwd1: this.model.password,
      pwd2: this.model.cpassword
    };

    console.log(JSON.stringify(registerData))
    this.userService.register(JSON.stringify(registerData));
  }
}

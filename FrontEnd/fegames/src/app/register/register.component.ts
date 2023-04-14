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

  }
}

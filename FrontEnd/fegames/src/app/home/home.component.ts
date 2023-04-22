import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    private router: Router, private userService: AccountService
   
  ) {}
  
  prueba() {
    this.router.navigate(['/prueba']);
    
}
}

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
   

    var user = this.model.username;
    var password = this.model.password;

     
    // this.userservice.getUserData(user, password)
    //   .subscribe((res: boolean) => {
    //     this.getData = res;
        

    //     if (this.getData == true) {
         
    //      this.router.navigate(["/home"])
    //     } else {
    //      alert("Invalid users")
    //     }
    //   });
}
}

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  username: String;
  match: String[] =[];


  constructor() {
    this.username = '';
    this.match = [];
  }
}

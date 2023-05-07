import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  username: String;
  match: String[] =[];
  type: any;

  

  constructor() {
    this.username = '';
    this.match = [];
    this.type = '';
  }
}

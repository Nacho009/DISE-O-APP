import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {
  username: String;
  match: String[] =[];
  type: any;
  id: String;

  

  constructor() {
    this.username = '';
    this.match = [];
    this.type = '';
    this.id='';
  }
}

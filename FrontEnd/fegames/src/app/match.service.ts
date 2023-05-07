import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  id!: String;
  ready!: boolean;
  players!: String[];
  
  constructor() { 

  }
}

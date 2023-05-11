import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedDataService } from '../shared-data.service';

@Component({
  selector: 'app-ganador-partida',
  templateUrl: './ganador-partida.component.html',
  styleUrls: ['./ganador-partida.component.css']
})
export class GanadorPartidaComponent {
  ganador: String;

  constructor(private router: Router,     private sharedDataService: SharedDataService
    ) {
    this.ganador = this.sharedDataService.username;
  }

  volverAlMenu() {
    this.router.navigate(['/home']); 
  }
}


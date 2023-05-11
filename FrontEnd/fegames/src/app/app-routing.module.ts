import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { JuegoComponent } from './juego/juego.component';
import { GanadorPartidaComponent } from './resultados/ganador-partida.component';

const routes: Routes = [

  {path: "login", component: LoginComponent},
  {path: "home", component: HomeComponent},
  {path: "register", component: RegisterComponent},
  {path: "", component: LoginComponent},
  {path: "juego", component: JuegoComponent},
  {path: "ganador", component: GanadorPartidaComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

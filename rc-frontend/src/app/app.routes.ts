import { Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";

export const AppRoutes: Routes = [

  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  }

  // { path: '**', component: PageNotFoundComponent }
];


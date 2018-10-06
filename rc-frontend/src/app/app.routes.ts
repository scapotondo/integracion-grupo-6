import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NewClaimComponent } from './components/new-claim/new-claim.component';
import {UserComponent} from './components/user/user.component';
import {ClaimComponent} from "./components/claim/claim.component";

export const AppRoutes: Routes = [

  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'new-claim',
    component: NewClaimComponent,
    pathMatch: 'full'
  },
  {
    path: 'user',
    component: UserComponent,
    pathMatch: 'full'
  },
  {
    path: 'claim',
    component: ClaimComponent,
    pathMatch: 'full'
  }

  // { path: '**', component: PageNotFoundComponent }
];


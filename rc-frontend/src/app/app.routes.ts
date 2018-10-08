import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NewClaimComponent } from './components/new-claim/new-claim.component';
import {UserComponent} from './components/user/user.component';
import {ClaimComponent} from "./components/claim/claim.component";
import {NewClaimClientComponent} from "./components/new-claim-client/new-claim-client.component";

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
  },
  {
    path: 'web-client/create-claim',
    component: NewClaimClientComponent,
    pathMatch: 'full'
  }

  // { path: '**', component: PageNotFoundComponent }
];


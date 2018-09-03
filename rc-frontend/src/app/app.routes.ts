import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NewClaimComponent } from './components/new-claim/new-claim.component';

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
  }

  // { path: '**', component: PageNotFoundComponent }
];


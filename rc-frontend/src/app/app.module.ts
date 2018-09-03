import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomMaterialModule } from './modules/custom-material-modules';
import { RouterModule } from '@angular/router';
import { AppRoutes } from './app.routes';
import { LoginComponent } from './components/login/login.component';
import { NewClaimComponent } from './components/new-claim/new-claim.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NewClaimComponent
  ],
  imports: [
    RouterModule.forRoot(AppRoutes),
    BrowserModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

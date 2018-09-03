import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { CustomMaterialModule } from './modules/custom-material-modules';
import { RouterModule } from '@angular/router';
import { AppRoutes } from './app.routes';
import { LoginComponent } from './components/login/login.component';
import {FormsModule} from "@angular/forms";
import {UIContext} from "./ui.context";
import {TokenStorage} from "./storage/token.storage";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./app.interceptor";
import {AuthService} from "./services/auth.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    RouterModule.forRoot(AppRoutes),
    BrowserModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    UIContext,
    TokenStorage,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

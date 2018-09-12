import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomMaterialModule } from './modules/custom-material-modules';
import { RouterModule } from '@angular/router';
import { AppRoutes } from './app.routes';
import { LoginComponent } from './components/login/login.component';
import { UIContext} from './ui.context';
import { TokenStorage} from './storage/token.storage';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './app.interceptor';
import { AuthService } from './services/auth.service';
import { NewClaimComponent } from './components/new-claim/new-claim.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserComponent } from './components/user/user.component';
import {UserService} from "./services/user.service";
import { OrderService } from './services/order.service';
import { ClaimService } from './services/claim.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NewClaimComponent,
    UserComponent
  ],
  imports: [
    RouterModule.forRoot(AppRoutes),
    BrowserModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    UIContext,
    TokenStorage,
    UserService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    OrderService,
    ClaimService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

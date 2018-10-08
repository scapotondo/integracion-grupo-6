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
import { UserDeleteComponent } from './components/user-delete/user-delete.component';
import {MAT_DIALOG_DATA, MatDialogModule, MatTableModule} from "@angular/material";
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { ClaimComponent } from './components/claim/claim.component';
import { ClaimCancelDialogComponent } from './components/claim-cancel-dialog/claim-cancel-dialog.component';
import { NewClaimClientComponent } from './components/new-claim-client/new-claim-client.component';
import { ClaimSearchComponent } from './components/claim-search/claim-search.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NewClaimComponent,
    UserDeleteComponent,
    UserComponent,
    UserEditComponent,
    UserCreateComponent,
    ClaimComponent,
    ClaimCancelDialogComponent,
    NewClaimClientComponent,
    ClaimSearchComponent
  ],
  imports: [
    RouterModule.forRoot(AppRoutes),
    BrowserModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule
  ],
  providers: [
    UIContext,
    TokenStorage  ,
    UserService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: MAT_DIALOG_DATA,
      useValue: {} // Add any data you wish to test if it is passed/used correctly
    },
    OrderService,
    ClaimService
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    UserDeleteComponent,
    UserEditComponent,
    UserCreateComponent,
    ClaimCancelDialogComponent,
    ClaimSearchComponent
  ],
  exports: [
    UserDeleteComponent,
    UserEditComponent,
    UserCreateComponent,
    ClaimCancelDialogComponent,
    ClaimSearchComponent
  ]
})
export class AppModule { }

import { Component, ComponentFactoryResolver } from '@angular/core';
import {UIContext} from "./ui.context";
import {TokenStorage} from "./storage/token.storage";
import { Router } from '@angular/router';
import {Location} from "@angular/common";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  authorized: boolean;

  constructor(public uiContext: UIContext,
              public tokenStorage: TokenStorage,
              private router: Router,
              private location: Location) {
    this.authorized = false;
    if(this.tokenStorage.getUser() == null && this.location.path() != '/web-client/create-claim'){
      this.router.navigate(['login']);
    } else if (this.location.path() == '/') {
      this.router.navigate(['claim']);
    }

    if(this.tokenStorage.getUser() != undefined) {
      this.authorized = (this.tokenStorage.getUser().userRol == 'ROLE_ADMIN');
    }
  }

  logOut() {
    this.tokenStorage.signOut();
    this.router.navigate(['login']);
  }
}

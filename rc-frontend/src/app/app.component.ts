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

  constructor(public uiContext: UIContext,
              public tokenStorage: TokenStorage,
              private router: Router,
              private location: Location) {

    if(this.tokenStorage.getUser() == null && this.location.path() != '/web-client/create-claim'){
      this.router.navigate(['login']);
    } else if (this.location.path() == '/') {
      this.router.navigate(['claim']);
    }
  }
}

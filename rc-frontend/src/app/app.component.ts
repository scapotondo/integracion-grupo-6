import { Component, ComponentFactoryResolver } from '@angular/core';
import {UIContext} from "./ui.context";
import {TokenStorage} from "./storage/token.storage";
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(public uiContext: UIContext,
              public tokenStorage: TokenStorage,
              private router: Router) {

    if(this.tokenStorage.getUser() == null){
      this.router.navigate(['login']);

    } else {
      this.router.navigate(['new-claim']);
    }
  }
}

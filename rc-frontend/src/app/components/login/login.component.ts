import { Component, OnInit } from '@angular/core';
import {UIContext} from "../../ui.context";
import {AuthService} from "../../services/auth.service";
import {TokenStorage} from "../../storage/token.storage";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  hide = true;

  constructor( private authService: AuthService,
               private tokenStorage: TokenStorage,
               private router: Router,
               private uiContext: UIContext) {
    this.uiContext.setShowToolbar(false);
  }

  ngOnInit() {

  }

  attemptLogin() {
    const that = this;
    this.authService.attemptAuth(this.username, this.password).subscribe(data => {
      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(that.username);
      console.log(data)
      this.router.navigate(['./pendientes']);
    });
  }
}

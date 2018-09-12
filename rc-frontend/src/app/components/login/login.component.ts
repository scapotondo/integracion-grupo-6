import { Component, OnInit } from '@angular/core';
import {UIContext} from '../../ui.context';
import {AuthService} from '../../services/auth.service';
import {TokenStorage} from '../../storage/token.storage';
import {Router} from '@angular/router';
import {UserStored} from '../../models/userStored.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string;
  password: string;
  hide = true;
  user: UserStored;
  loginError: boolean;

  constructor( private authService: AuthService,
               private tokenStorage: TokenStorage,
               private router: Router,
               private uiContext: UIContext) {
    this.uiContext.setShowToolbar(false);
    this.loginError = false;
  }

  attemptLogin() {
    const that = this;
    this.authService.attemptAuth(this.username, this.password).subscribe(data => {
      this.loginError = false;
      this.tokenStorage.saveToken(data.token);
      this.user = {
          username: data.username,
          userRol: data.userRole
      };
        this.tokenStorage.saveUser(this.user);
      this.uiContext.setShowToolbar(true);
      this.router.navigate(['./new-claim']);

    }, err => {
      this.username = '';
      this.password = '';
      this.loginError = true;
    });
  }
}

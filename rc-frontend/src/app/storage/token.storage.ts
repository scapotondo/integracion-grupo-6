import { Injectable } from '@angular/core';

@Injectable()
export class TokenStorage {

  readonly TOKEN_KEY = 'AuthToken';
  readonly USER_KEY = 'User';
  readonly TOKEN_HEADER_KEY = 'Authorization';

  constructor() {
  }

  public isLoggedIn = false;

  signOut() {
    window.sessionStorage.removeItem(this.TOKEN_KEY);
    window.sessionStorage.clear();

    this.isLoggedIn = false;
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(this.TOKEN_KEY);
    window.sessionStorage.setItem(this.TOKEN_KEY, token);

    this.isLoggedIn = token !== undefined;
  }

  public saveUser(user: string) {
    window.sessionStorage.removeItem(this.USER_KEY);
    window.sessionStorage.setItem(this.USER_KEY, user);
  }

  public getToken(): string {
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  public getUser(): string {
    return sessionStorage.getItem(this.USER_KEY);
  }

  public getFormattedToken(): string {
    return 'Bearer ' + this.getToken();
  }
}

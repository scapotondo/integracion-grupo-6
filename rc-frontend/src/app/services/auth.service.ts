import { Injectable } from '@angular/core';
import {Observable} from "rxjs/internal/Observable";
import {HttpClient} from "@angular/common/http";
import {LoginUser} from "../models/loginUser.model";
import * as Global from '../global';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl = Global.server + 'token/';

  constructor(private http: HttpClient) { }

  public attemptAuth(ussername: string, password: string): Observable<any> {
    const credentials: LoginUser = {username: ussername, password: password};
    return this.http.post(this.baseUrl, credentials);
  }

}

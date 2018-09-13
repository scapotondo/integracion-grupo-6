import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/internal/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import * as Global from '../global';
import {User} from '../models/user.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  private userUrl = Global.server + 'user/';

  constructor(private http: HttpClient) {}

  public create(user) {
    return this.http.post<User>(this.userUrl, user);
  }

  public update(user) {
    return this.http.put<User>(this.userUrl, user);
  }

  public delete(user: User) {
    return this.http.delete(this.userUrl + user.username);
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.userUrl + 'all');
  }

}

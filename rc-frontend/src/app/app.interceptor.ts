
import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { TokenStorage } from './storage/token.storage';
import 'rxjs/add/operator/do';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private tokenStorage: TokenStorage,
              private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    if (this.tokenStorage.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(this.tokenStorage.TOKEN_HEADER_KEY, this.tokenStorage.getFormattedToken())});
    }

    return next.handle(authReq).do((data) => {
      console.log(data);
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          this.router.navigate(['login']);
        }
      }
    });
  }

}

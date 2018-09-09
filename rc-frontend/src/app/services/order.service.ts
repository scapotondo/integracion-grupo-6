import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import * as Global from '../global';
import { Order } from '../models/order.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private orderUrl = Global.server + 'order/';

  constructor(private http: HttpClient) {
  }

  public getOrder(orderNbr: string): Observable<Order> {
    return this.http.get<Order>(this.orderUrl + orderNbr);
  }
}

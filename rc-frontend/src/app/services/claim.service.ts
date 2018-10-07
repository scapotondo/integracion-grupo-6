import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import * as Global from '../global';
import { ClaimStatus } from '../models/claimStatus.mode';
import { ClaimType } from '../models/claimType.model';
import { ClaimOrigin } from '../models/claimOrigin.model';
import { Claim } from '../models/claim.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  private claimUrl = Global.server + 'claim/';

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Claim[]> {
    return this.http.get<Claim[]>(this.claimUrl);
  }

  public findStatuses(): Observable<ClaimStatus[]> {
    return this.http.get<ClaimStatus[]>(this.claimUrl + 'statuses');
  }

  public findTypes(): Observable<ClaimType[]> {
    return this.http.get<ClaimType[]>(this.claimUrl + 'types');
  }

  public getOrigins(): Observable<ClaimOrigin[]> {
    return this.http.get<ClaimOrigin[]>(this.claimUrl + 'origins');
  }

  public getClaimByOrder(orderNbr: string): Observable<Claim> {
    return this.http.get<Claim>(this.claimUrl + 'order/' + orderNbr);
  }

  public create(claim) {
    return this.http.post<Claim>(this.claimUrl, claim);
  }

  public cancel(claim) {
    return this.http.get<Claim>(this.claimUrl + 'cancel/' + claim.id);
  }
}

import { Component, OnInit } from '@angular/core';
import {Claim} from "../../models/claim.model";
import {ClaimService} from "../../services/claim.service";
import {UIContext} from "../../ui.context";
import {Router} from "@angular/router";

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {

  claims: Claim[];
  displayedColumns: string[] = ['description', 'origin', 'type', 'status', 'action'];

  constructor(private claimService: ClaimService,
              public uiContext: UIContext,
              private router: Router) {
    this.uiContext.setTittle('Reclamos');
  }

  ngOnInit() {
    this.claimService.findAll().subscribe(data => {
      this.claims = data;
      console.log(this.claims);
    });
  }


  addClaim() {
    this.router.navigate(['./new-claim'])
  }

  cancelClaim() {

  }

}

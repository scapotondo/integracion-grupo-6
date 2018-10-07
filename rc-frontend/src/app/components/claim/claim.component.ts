import {Component, OnInit} from '@angular/core';
import {Claim} from "../../models/claim.model";
import {ClaimService} from "../../services/claim.service";
import {UIContext} from "../../ui.context";
import {Router} from "@angular/router";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {UserDeleteComponent} from "../user-delete/user-delete.component";
import {ClaimCancelDialogComponent} from "../claim-cancel-dialog/claim-cancel-dialog.component";

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {

  claims: Claim[];
  displayedColumns: string[] = ['description', 'origin', 'type', 'status', 'action'];
  dialogConfig = new MatDialogConfig();

  constructor(private claimService: ClaimService,
              public uiContext: UIContext,
              private router: Router,
              public dialog: MatDialog) {
    this.uiContext.setTittle('Reclamos');
    this.dialogConfig.disableClose = true;
    this.dialogConfig.autoFocus = true;
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

  cancelClaim(claim) {
    let that = this;
    this.dialog.open(ClaimCancelDialogComponent,{
      width: '250px',
      data: claim
    }).afterClosed()
      .subscribe(response => {
        let claims2 = that.claims;
        const index: number = claims2.findIndex(x => x.id == response.data.id);
        claims2[index] = response.data;
        that.claims = claims2;
      });
  }

}

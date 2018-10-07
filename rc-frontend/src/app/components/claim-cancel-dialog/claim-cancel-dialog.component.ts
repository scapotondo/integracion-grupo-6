import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Claim} from "../../models/claim.model";
import {ClaimService} from "../../services/claim.service";

@Component({
  selector: 'app-claim-cancel-dialog',
  templateUrl: './claim-cancel-dialog.component.html',
  styleUrls: ['./claim-cancel-dialog.component.css']
})
export class ClaimCancelDialogComponent implements OnInit {


  claim: Claim;

  constructor(public dialog: MatDialogRef<ClaimCancelDialogComponent>,
              public claimService: ClaimService,
              @Inject(MAT_DIALOG_DATA) public data) {
    this.claim = data;
  }

  ngOnInit() {
  }



  onNoClick() {
    this.dialog.close();
  }

  onYesClick() {
    this.claimService.cancel(this.claim).subscribe( response => {
      this.dialog.close({ data: this.claim });
    });

  }
}

import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
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
              private router: Router,
              private changeDetectorRefs: ChangeDetectorRef) {
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

  cancelClaim(claim) {
    this.claimService.cancel(claim).subscribe(data => {
      let claims2 = this.claims;
      const index: number = claims2.findIndex(x => x.id == data.id);
      claims2[index] = data;
      this.claims = claims2;
      this.refresh();

    });
  }

  refresh() {
      this.changeDetectorRefs.detectChanges();
  }

}

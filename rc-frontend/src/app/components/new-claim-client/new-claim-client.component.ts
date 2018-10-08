import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClaimType} from "../../models/claimType.model";
import {Order} from "../../models/order.model";
import {Router} from "@angular/router";
import {OrderService} from "../../services/order.service";
import {ClaimOrigin} from "../../models/claimOrigin.model";
import {ClaimService} from "../../services/claim.service";
import {Claim} from "../../models/claim.model";
import {MatStepper} from "@angular/material";

@Component({
  selector: 'app-new-claim-client',
  templateUrl: './new-claim-client.component.html',
  styleUrls: ['./new-claim-client.component.css']
})
export class NewClaimClientComponent implements OnInit {
  order: Order;
  types: ClaimType[];
  orderNumber: string;
  errorMessage: string;
  origins: ClaimOrigin[];
  selectedType: ClaimType;
  clientIdentifier: string;
  claimDescription: string;
  selectedOrigin: ClaimOrigin;
  createClaimFormGroup: FormGroup;
  orderDetailsFormGroup: FormGroup;
  existentClaim: Claim;

  @ViewChild('stepper') stepper: MatStepper;

  constructor(public _formBuilder: FormBuilder,
              private orderService: OrderService,
              private claimService: ClaimService,
              private router: Router) {

    this.resetValues();
  }

  ngOnInit() {
    const that = this;

    this.stepper.selectionChange.subscribe(selection => {
      if (selection.selectedStep.label === 'firstStep') {
        this.resetValues();
      } else if (selection.selectedStep.label === 'searchOrder') {
        this.searchOrder();
      }
    });

    this.orderDetailsFormGroup = this._formBuilder.group({
      clientId: ['', Validators.required],
      orderNbr: ['', Validators.required]
    });

    this.createClaimFormGroup = this._formBuilder.group({
      originCombo: ['', Validators.required],
      typeCombo: ['', Validators.required],
      textDescription: ['', Validators.required]
    });

    this.claimService.findTypes().subscribe(types => {
      that.types = types;
    });
  }

  resetValues() {
    this.order = null;
    this.errorMessage = '';
    this.existentClaim = null;
  }

  searchOrder() {
    const that = this;
    this.resetValues();

    this.orderService.getOrder(this.orderNumber).subscribe(order => {
      if (order === null) {
        that.errorMessage = 'El código de orden ingresado no existe';
      } else if (order.client != null && order.client.identification === that.clientIdentifier.toString()) {
        that.order = order;

        that.claimService.getClaimByOrder(that.order.id.toString()).subscribe(claim => {
          that.existentClaim = claim;
        });
      } else {
        that.errorMessage = 'La orden no corresponde al DNI ingresado';
      }
    }, err => {
      if (err.status === 500) {
        that.errorMessage = 'El código de orden ingresado no existe';
      } else if (err.status === 400) {
        that.errorMessage = 'Los datos ingresados no son validos o no tienen el formato correcto';
      }
    });

  }

  createClaim() {
    let claim: Claim;
    claim = {
      id: 0,
      origin: null,
      type: this.selectedType,
      status: null,
      description: this.claimDescription,
      orderId: this.order.id,
      clientIdentification: this.order.client.identification
    };

    this.claimService.webCreate(claim).subscribe(
      response => this.router.navigate(['./new-claim']),
      err => console.log(err)
    );

  }

}

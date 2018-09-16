import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Order } from '../../models/order.model';
import { OrderService } from '../../services/order.service';
import { MatStepper } from '@angular/material';
import { ClaimService } from '../../services/claim.service';
import { ClaimStatus } from '../../models/claimStatus.mode';
import { ClaimType } from '../../models/claimType.model';
import { ClaimOrigin } from '../../models/claimOrigin.model';
import { Claim } from '../../models/claim.model';

@Component({
  selector: 'app-new-claim',
  templateUrl: './new-claim.component.html',
  styleUrls: ['./new-claim.component.css']
})
export class NewClaimComponent implements OnInit {
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

  @ViewChild('stepper') stepper: MatStepper;

  constructor(public _formBuilder: FormBuilder,
    private orderService: OrderService,
    private claimService: ClaimService) {

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

    this.claimService.getOrigins().subscribe(origins => {
      that.origins = origins;
    });
  }

  resetValues() {
    this.order = null;
    this.errorMessage = '';
  }

  searchOrder() {
    const that = this;
    this.resetValues();

    this.orderService.getOrder(this.orderNumber).subscribe(order => {
      if (order === null) {
        that.errorMessage = 'El codigo de orden ingresado no existe';
      } else if (order.client != null && order.client.identification === that.clientIdentifier.toString()) {
        that.order = order;
      } else {
        that.errorMessage = 'La orden no corresponde al DNI ingresado';
      }
    }, err => {
      if (err.status === 500) {
        that.errorMessage = 'El codigo de orden ingresado no existe';
      } else if (err.status === 400) {
        that.errorMessage = 'Los datos ingresados no son validos o no tienen el formato correcto';
      }
    });

  }

  createClaim() {
    let claim: Claim;

    claim = {
      id: 0,
      origin: this.selectedOrigin,
      type: this.selectedType,
      status: null,
      description: this.claimDescription,
      orderId: this.order.id,
      clientIdentification: this.order.client.identification
    };

    console.log(claim);

    this.claimService.create(claim).subscribe(
      response => console.log(response),
      err => console.log(err)
    );

  }

}

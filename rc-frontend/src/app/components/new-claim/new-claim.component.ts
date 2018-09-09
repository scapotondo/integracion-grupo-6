import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Order } from '../../models/order.model';
import { OrderService } from '../../services/order.service';

@Component({
  selector: 'app-new-claim',
  templateUrl: './new-claim.component.html',
  styleUrls: ['./new-claim.component.css']
})
export class NewClaimComponent implements OnInit {
  orderDetailsFormGroup: FormGroup;
  createClaimFormGroup: FormGroup;
  order: Order;
  clientIdentifier: string;
  orderNumber: string;

  constructor(private _formBuilder: FormBuilder, private orderService: OrderService) {
  }

  ngOnInit() {
    this.orderDetailsFormGroup = this._formBuilder.group({
      clientId: ['', Validators.required],
      orderNbr: ['', Validators.required]
    });
    this.createClaimFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
  }

  buscarOrden() {
    // TODO: buscar orden usando clientId y orderNbr
    this.orderService.getOrder(this.orderNumber).subscribe(() => {

    });

    // this.order = {
    //   id: 111,
    //   client: {
    //     identification: '32234234',
    //     fullName: 'Pepe Whatever',
    //     email: 'pepe@gmail.com'
    //   },
    //   status: {
    //     id: 1,
    //     name: 'Pendiente'
    //   },
    //   product: {
    //     id: 1,
    //     description: 'Heladera'
    //   }
    // };
  }

  createClaim() {

    // TODO: crear reclamo
    // private User user; Es el que esta loggeado

    // private Order order; Sale de order

    // private ClaimOrigin claimOrigin;

    // private ClaimType claimType;

    // private ClaimStatus claimStatus; Nuevo o algo asi

    // private String description;

    // private Date creationDate; // Fecha actual
  }
}

import { Component } from '@angular/core';
import { Delivery } from './delivery';
import { DeliveryService } from './delivery.service';


@Component({
	selector: 'deliveryform',
	templateUrl: 'app/deliveries/deliveryform.html'
})
export class DeliveryForm {

	private model = new Delivery();

	constructor(private _deliveryService: DeliveryService) {

	}

	submitDelivery() {
		console.log("in submit delivery");
		this._deliveryService.newDelivery(this.model).subscribe(
		err => {console.log(err)});




		this.model = new Delivery();
	}

	get diagnostic() { return JSON.stringify(this.model); }

}

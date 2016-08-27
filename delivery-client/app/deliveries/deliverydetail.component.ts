import { Component, OnInit, OnDestroy  } from '@angular/core';
import { Router, ActivatedRoute }       from '@angular/router';

import { Delivery } from './delivery';
import { DeliveryService } from './delivery.service';

@Component({
	templateUrl: 'app/deliveries/deliverydetail.html'
})
export class DeliveryDetail {
	delivery: Delivery;
	private sub: any;

	constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: DeliveryService) {}

	ngOnInit() {
		this.sub = this.route.params.subscribe(params => {
			let id = +params['id']; // (+) converts string 'id' to a number
			this.service.getDelivery(id).subscribe(
				data => {this.delivery = data},
				err => {console.log(err)}
			);
		});

	}

	ngOnDestroy() {
		this.sub.unsubscribe();
	}

	gotoDeliveries() {
		this.router.navigate(['/open-deliveries']);
	}

}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Delivery } from './delivery';
import { DeliveryService} from './delivery.service';

declare var SockJS: any;
declare var Stomp: any;


@Component({
    selector: 'deliverytable',
    templateUrl: 'app/deliveries/deliverytable.html'
})
export class DeliveryTable implements OnInit, OnDestroy {
	public stompClient: Stomp;
	public endpoint = "http://localhost:8080/delivery-ws";
	public channelPath = "/topic/openDeliveries";
	public deliveries = [];
	public connected: boolean;



	constructor(private router: Router, private _deliveryService: DeliveryService) {
		this.stompClient = Stomp.over(new SockJS(this.endpoint));
		let stompClient = this.stompClient;
		let channelPath = this.channelPath;
		let response = this.response;
		this.stompClient.connect({}, (frame) => {
                console.log('Connected: ' + frame);
				this.connected = true;
                stompClient.subscribe(channelPath, (deliveryList) => {

					//this.deliveries = deliveryList.body;
					this.deliveries = JSON.parse(deliveryList.body);
                });
            });

	}

	public setResponse(x: []) {
		console.log('in response function');
		this.deliveries = x;
	}

	ngOnDestroy() {
		if (this.connected) {
			this.stompClient.disconnect();
			this.connected = false;
			
		}
	}

	ngOnInit() {
		this._deliveryService.getAllOpenDeliveries().subscribe(
				data => {this.deliveries = data},
				err => {console.log(err)}
		);
	}

	onSelect(delivery: Delivery) {
		this.router.navigate(['/delivery', delivery.id]);
	}



}

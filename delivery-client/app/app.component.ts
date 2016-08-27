import { Component } from '@angular/core';
import { Delivery } from './deliveries/delivery';
import { DeliveryService} from './deliveries/delivery.service';
import { DeliveryTable } from './deliveries/deliverytable.component';
import { DeliveryForm } from './deliveries/deliveryform.component';
import { Nav } from './nav.component';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { ContactComponent } from './contact.component'

declare var SockJS: any;
declare var Stomp: any;


@Component({
    selector: 'my-app',
    templateUrl: 'app/app.html',
	directives: [ROUTER_DIRECTIVES, DeliveryTable, DeliveryForm, Nav, ContactComponent]
})
export class AppComponent {



}

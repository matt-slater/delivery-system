import { provideRouter, RouterConfig } from '@angular/router';

import { DeliveryForm } from './deliveries/deliveryform.component';
import { ContactComponent } from './contact.component';
import { deliveriesRoutes } from './deliveries/deliveries.routes';

const routes: RouterConfig = [
	...deliveriesRoutes,
	{ path: '', redirectTo: '/open-deliveries', pathMatch: 'full'},
	{ path: 'add-delivery', component: DeliveryForm },
	{ path: 'about', component: ContactComponent }
];

export const appRouterProviders = [
	provideRouter(routes)
];

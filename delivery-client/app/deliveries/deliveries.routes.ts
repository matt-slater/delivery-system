import { RouterConfig }          from '@angular/router';
import { DeliveryTable }     from './deliverytable.component';
import { DeliveryDetail }   from './deliverydetail.component';
export const deliveriesRoutes: RouterConfig = [
  { path: 'open-deliveries',  component: DeliveryTable },
  { path: 'delivery/:id', component: DeliveryDetail }
];

import { bootstrap }    from '@angular/platform-browser-dynamic';
import { DeliveryService } from './deliveries/delivery.service';
import {HTTP_PROVIDERS} from '@angular/http';
import { disableDeprecatedForms, provideForms } from '@angular/forms';
import { appRouterProviders } from './app.routes';


import { AppComponent } from './app.component';

bootstrap(AppComponent, [HTTP_PROVIDERS, appRouterProviders, disableDeprecatedForms(), provideForms(), DeliveryService]);

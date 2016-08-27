import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Delivery } from './delivery';

@Injectable()
export class DeliveryService {
  constructor(private http: Http) { }

  getDelivery(id: String): Observable<Delivery> {
    return this.http.get('http://localhost:8080/' + id)
      .map(this.extractData)
      .catch(this.handleError);
  }

  newDelivery(delivery: Delivery): Observable<Delivery> {
    let body = JSON.stringify(delivery);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });


    console.log("in post method");
    return this.http.post('http://localhost:8080/new', body, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getAllOpenDeliveries(): Observable<Delivery[]> {
    return this.http.get('http://localhost:8080/opendeliveries')
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body;
  }

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);

  }
}

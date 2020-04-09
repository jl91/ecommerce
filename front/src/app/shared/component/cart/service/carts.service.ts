import {Injectable} from '@angular/core';
import {Product} from '../../../../core/products/product.model';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartsService {

  private addProductSubject: Subject<Product> = new Subject<Product>();

  private removeProductSubject: Subject<Product> = new Subject<Product>();

  constructor() {
  }

  public get addProductObservable(): Observable<Product> {
    return this.addProductSubject.asObservable();
  }

  public get removeProductObservable(): Observable<Product> {
    return this.removeProductSubject.asObservable();
  }

  public add(product: Product): void {
    this.addProductSubject.next(product);
  }

  public remove(product: Product): void {
    this.removeProductSubject.next(product);
  }

}

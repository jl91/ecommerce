import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';
import {Product} from './product.model';

@Injectable()
export class ProductsHttpService {

  private readonly BASE_URL: string = `${environment.apiBaseUrl}/products`;

  constructor(private httpClient: HttpClient) {
  }

  public get all(): Observable<Array<Product>> {
    return this.httpClient
      .get(this.BASE_URL)
      .pipe(map((data: any) => data as Array<Product>));
  }

  public save(product: Product): Observable<Product> {

    if (!product.id) {
      return this.httpClient
        .post(this.BASE_URL, product)
        .pipe(map((data: Product) => data as Product));
    }

    return this.httpClient
      .put(this.BASE_URL, product)
      .pipe(map((data: Product) => data as Product));
  }

}

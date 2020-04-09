import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {map} from 'rxjs/operators';

@Injectable()
export class ProductsHttpService {

  private readonly BASE_URL: string = `${environment.apiBaseUrl}/products`;

  constructor(private httpClient: HttpClient) {
  }


  public get all(): Observable<Array<any>> {
    return this.httpClient
      .get(this.BASE_URL)
      .pipe(map((data: any) => data));
  }

}
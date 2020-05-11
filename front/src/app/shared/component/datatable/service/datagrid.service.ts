import {Injectable, Input} from '@angular/core';
import {HttpOptions} from '../model/http-options.model';
import {Observable} from 'rxjs';

@Injectable()
export class DatagridService {

  public httpOptions: HttpOptions;

  constructor() {
  }

  public fetchBy(params: Object): Observable<any>{
    const {service, fetchAllMethod} = this.httpOptions;
    return service[fetchAllMethod](params);
  }
}

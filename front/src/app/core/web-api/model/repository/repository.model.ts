import {Observable} from 'rxjs';

export interface Repository<T> {

  by(params?: any): Observable<Array<T>>;

  byId(id: number): Observable<T>;

  save(data: T): Observable<T>;

  remove(id: number): Observable<any>;
}
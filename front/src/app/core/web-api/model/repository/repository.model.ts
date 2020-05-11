import {Observable} from 'rxjs';
import {QueryBuilderService} from '../../query/query-builder.service';

export interface Repository<T> {

  by(params?: any): Observable<Array<T>>;

  byId(id: number): Observable<T>;

  save(data: T): Observable<T>;

  remove(id: number): Observable<any>;

  query(queryBuilderService: QueryBuilderService): Observable<any>;
}

import {Observable} from 'rxjs';
import {QueryBuilder} from '../query/query-builder.model';
import {Resultset} from '../resultset/resultset.model';

export interface Repository<T> {

  by(params?: any): Observable<Resultset<T>>;

  byId(id: number): Observable<Resultset<T>>;

  save(data: T): Observable<T>;

  remove(id: number): Observable<any>;

  query(queryBuilderService: QueryBuilder): Observable<Resultset<T>>;
}

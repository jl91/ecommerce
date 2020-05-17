import {Injectable} from '@angular/core';
import {HttpOptions} from '../model/http-options.model';
import {Observable} from 'rxjs';
import {QueryBuilderService} from '../../../../core/web-api/query/query-builder.service';
import {QueryBuilder} from '../../../../core/web-api/model/query/query-builder.model';
import {Resultset} from '../../../../core/web-api/model/resultset/resultset.model';

@Injectable()
export class DatagridService {

  public httpOptions: HttpOptions;
  private queryBuilder: QueryBuilderService;

  constructor() {
    this.queryBuilder = new QueryBuilderService();
  }

  public getQueryBuilder(): QueryBuilder {
    return this.queryBuilder;
  }

  public fetchBy(queryBuilder: QueryBuilder): Observable<Resultset<any>> {
    const {service} = this.httpOptions;
    return service.query(queryBuilder);
  }

}

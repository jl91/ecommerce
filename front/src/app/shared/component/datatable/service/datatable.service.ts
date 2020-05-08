import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {Column} from '../model/column.model';
import {Row} from '../model/row.model';

@Injectable()
export class DatatableService {

  public readonly PAGE_SIZE_OPTIONS: Array<number> = [10, 20, 50, 100];
  private columnsChanged: Subject<Array<Column>> = new Subject<Array<Column>>();
  private rowsChanged: Subject<Array<Row<any>>> = new Subject<Array<Row<any>>>();

  constructor() {
  }

  public get columnsChangedObservable(): Observable<Array<Column>> {
    return this.columnsChanged.asObservable();
  }

  public get rowsChangedObservable(): Observable<Array<Row<any>>> {
    return this.rowsChanged.asObservable();
  }

  public sendColumns(columns: Array<Column>): void {
    this.columnsChanged.next(columns);
  }

  public sendRows(rows: Array<Row<any>>): void {
    this.rowsChanged.next(rows);
  }

}

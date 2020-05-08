import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {Column} from '../model/column.model';
import {Row} from '../model/row.model';
import {ColumnTypeEnum} from '../model/column-type.enum';

@Injectable()
export class DatatableService {

  public readonly PAGE_SIZE_OPTIONS: Array<number> = [
    10,
    20,
    50,
    100
  ];

  public readonly SYSTEM_COLUMNS: Array<string> = [
    'select',
    'actions'
  ];

  public columns: Array<Column> = [];

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

  public isColumnText(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.TEXT);
  }

  public isColumnLongText(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.LONG_TEXT);
  }

  public isColumnNumber(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.NUMBER);
  }

  public isColumnMoney(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.MONEY);
  }

  public isColumnChips(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.CHIPS);
  }

  public isColumnDate(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.DATE);
  }

  public isColumnTime(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.TIME);
  }

  public isColumnDatetime(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.DATETIME);
  }

  public isColumnBoolean(name: string): boolean {
    return this.isColumn(name, ColumnTypeEnum.BOOLEAN);
  }

  private isColumn(name: string, columType: ColumnTypeEnum): boolean {
    return this.columns
        ?.find(column => column.value === name)
        ?.type === columType
      || false;
  }

  public isColumnEditableInline(name: string): boolean {
    return this.columns
      ?.find(column => column.value === name)
      ?.isEditable;
  }



}

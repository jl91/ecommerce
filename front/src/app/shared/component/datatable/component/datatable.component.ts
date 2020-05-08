import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges
} from '@angular/core';
import {Column} from '../model/column.model';
import {Row} from '../model/row.model';
import {DatatableService} from '../service/datatable.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-datatable',
  templateUrl: 'datatable.component.html',
  styleUrls: [
    './datatable.component.scss'
  ],
  providers: [
    DatatableService
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DatatableComponent implements OnInit, OnChanges, OnDestroy, AfterViewInit {

  @Input()
  public columns: Array<Column> = [];

  @Input()
  public rows: Array<any> = [];

  @Input()
  public showPagination = true;

  public displayedColumns: Array<string>;

  public displayedRows: Array<Row<any>>;

  public readonly PAGE_SIZE_OPTIONS: Array<number> = [10, 20, 50, 100];

  private subscriptions: Subscription = new Subscription();

  constructor(
    private datatableService: DatatableService,
    private changeDetectorRef: ChangeDetectorRef
  ) {
  }

  public ngOnInit() {
    this.processColumns();
    this.registerOnColumnsChanged();
    this.registerOnRowsChanged();
  }

  public ngOnChanges(changes: SimpleChanges): void {
    this.afterChanges(changes);
  }

  public ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  public ngAfterViewInit(): void {
    this.changeDetectorRef.detach();
  }

  private registerOnColumnsChanged(): void {
    const subscription = this.datatableService
      .columnsChangedObservable
      .subscribe((columns: Array<Column>) => {
        this.columns = columns;
        this.processColumns();
        this.updateView();
      });
    this.subscriptions.add(subscription);
  }

  private registerOnRowsChanged(): void {
    const subscription = this.datatableService
      .rowsChangedObservable
      .subscribe((rows: Array<Row<any>>) => {
        this.rows = rows;
        this.processRows();
        this.updateView();
      });
    this.subscriptions.add(subscription);
  }

  private afterChanges(changes: SimpleChanges): void {
    if (changes.columns.currentValue) {
      this.processColumns();
    }

    if (changes.rows.currentValue) {
      this.processRows();
    }

    this.updateView();
  }

  private processRows(): void {
    this.displayedRows = this.rows
      .map(row => ({
          value: row
        } as Row<any>)
      );
  }

  private processColumns(): void {
    this.displayedColumns = this.columns
      .sort((current: Column, next: Column) => current.order - next.order)
      .map(column => column.value);
  }

  private updateView(): void {
    this.changeDetectorRef.reattach();
    this.changeDetectorRef.detectChanges();
    this.changeDetectorRef.detach();
  }


}

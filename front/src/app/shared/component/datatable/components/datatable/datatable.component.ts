import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  Output,
  SimpleChanges
} from '@angular/core';
import {Column} from '../../model/column.model';
import {Row} from '../../model/row.model';
import {DatatableService} from '../../service/datatable.service';
import {Subscription} from 'rxjs';
import {SelectionModel} from '@angular/cdk/collections';
import {MatCheckboxChange} from '@angular/material/checkbox';
import {debounceTime} from 'rxjs/operators';
import {EditCell} from '../../model/edit-cell.model';
import {IconDefinition} from '@fortawesome/fontawesome-common-types';
import {faFileExport, faSync} from '@fortawesome/free-solid-svg-icons';
import {Pagination} from '../../model/pagination.model';
import {PageEvent} from '@angular/material/paginator/paginator';
import {ColumnSort} from '../../model/column-sort.model';
import {WidthMap} from '../../model/width-map.model';

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

  @Input()
  public showSelectColumn = true;

  @Input()
  public showActionsColumn = true;

  @Input()
  public isEditable = true;

  @Input()
  public currentPage = 1;

  @Input()
  public itemsPerPage = 10;

  @Input()
  public total = 100;

  @Input()
  public isLoading = false;

  @Output()
  public selectedRows: EventEmitter<Array<Row<any>>> = new EventEmitter<Array<Row<any>>>();

  @Output()
  public editInline: EventEmitter<EditCell> = new EventEmitter<EditCell>();

  @Output()
  public paginationChanged: EventEmitter<Pagination> = new EventEmitter<Pagination>();

  @Output()
  public reloaded: EventEmitter<void> = new EventEmitter<void>();

  @Output()
  public sorted: EventEmitter<ColumnSort> = new EventEmitter<ColumnSort>();

  public displayedColumns: Array<string> = [];

  public displayedRows: Array<Row<any>> = [];

  public selection = new SelectionModel<any>(true, []);

  public iterableColumns: Array<string> = [];

  public widthMap: WidthMap = {};

  public readonly ICONS: { [prop: string]: IconDefinition } = {
    export: faFileExport,
    sync: faSync
  };

  private subscriptions: Subscription = new Subscription();

  constructor(
    public datatableService: DatatableService,
    private changeDetectorRef: ChangeDetectorRef,
    public elementRef: ElementRef
  ) {
  }

  public setIterableColumns(): Array<string> {
    return this.displayedColumns
      .filter(column => !this.datatableService
        .SYSTEM_COLUMNS
        .includes(column)
      );
  }

  public ngOnInit() {
    this.configureDatatableService();
    this.processColumns();
    this.registerOnColumnsChanged();
    this.registerOnRowsChanged();
    this.registerOnSelectionChanged();
  }

  public ngOnChanges(changes: SimpleChanges): void {
    this.afterChanges(changes);
  }

  public ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  public ngAfterViewInit(): void {
    this.updateView();
  }

  public isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.displayedRows.length;
    return numSelected === numRows;
  }

  public masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.displayedRows.forEach(row => this.selection.select(row));
    this.updateView();
  }

  public checkboxLabel(row?: Row<any>): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  public toggleSelection($event: MatCheckboxChange, row: Row<any>): void {
    if ($event) {
      this.selection.toggle(row);
    }
    this.updateView();
  }

  public onEditInline(event: EditCell): void {
    this.editInline.emit(event);
  }

  public onCurrentPageChange(pageEvent: PageEvent): void {
    this.paginationChanged
      .emit({
        currentPage: pageEvent.pageIndex + 1,
        itemsPerPage: pageEvent.pageSize
      });
  }

  private configureDatatableService(): void {
    this.datatableService.columns = this.columns;
    this.datatableService.total = this.total;
    this.datatableService.itemsPerPage = this.itemsPerPage;
    this.datatableService.currentPage = this.currentPage;
    this.datatableService.setPageSizeOptions();
  }

  private registerOnColumnsChanged(): void {
    const subscription = this.datatableService
      .columnsChangedObservable
      .subscribe((columns: Array<Column>) => {
        this.setColumns(columns);
        this.processColumns();
        this.updateView();
      });
    this.subscriptions.add(subscription);
  }

  private setColumns(columns: Array<Column>): void {
    this.columns = columns;
    this.datatableService.columns = columns;
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

    if (changes?.columns?.currentValue) {
      this.processColumns();
    }

    if (changes?.rows?.currentValue) {
      this.processRows();
    }

    this.configureDatatableService();

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
    this.datatableService.columns = this.columns;
    this.displayedColumns = this.columns
      .sort((current: Column, next: Column) => current.order - next.order)
      .map(column => column.value);

    if (this.showSelectColumn) {
      this.displayedColumns.unshift('select');
    }

    if (this.showActionsColumn) {
      this.displayedColumns.push('actions');
    }

    this.iterableColumns = this.setIterableColumns();
    this.mapColumnsWidth();
  }

  private mapColumnsWidth(): void {
    this.columns
      .forEach(column => {
        const {value, minWidth, width, maxWidth} = column;

        this.widthMap[value] = {
          minWidth: undefined,
          width: undefined,
          maxWidth: undefined,
        };

        if (minWidth) {
          this.widthMap[value].minWidth = minWidth;
        }

        if (width) {
          this.widthMap[value].width = width;
        }

        if (maxWidth) {
          this.widthMap[value].maxWidth = maxWidth;
        }

      });
  }

  private updateView(): void {
    this.changeDetectorRef.detectChanges();
  }

  private registerOnSelectionChanged(): void {
    const subscription = this.selection
      .changed
      .pipe(debounceTime(100))
      .subscribe(() => this.selectedRows.emit(this.selection.selected));

    this.subscriptions.add(subscription);
  }

  public onReload(mouseEvent: MouseEvent): void {
    mouseEvent.preventDefault();
    this.reloaded.emit();
  }

  public onSortChanged($event): void {
    const {active, direction} = $event;

    const type = direction.toString().toUpperCase();

    this.sorted.emit({
      column: active,
      type
    });
  }

}

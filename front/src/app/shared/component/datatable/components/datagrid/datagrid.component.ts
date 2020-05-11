import {AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {Column} from '../../model/column.model';
import {Row} from '../../model/row.model';
import {Product} from '../../../../../core/web-api/endpoints/products/product.model';
import {EditCell} from '../../model/edit-cell.model';
import {Pagination} from '../../model/pagination.model';
import {HttpOptions} from '../../model/http-options.model';
import {DatagridService} from '../../service/datagrid.service';

@Component({
  selector: 'app-datagrid',
  templateUrl: 'datagrid.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [
    DatagridService
  ]
})
export class DatagridComponent implements OnInit, AfterViewInit {

  @Input()
  public columns: Array<Column> = [];

  @Input()
  public httpOptions: HttpOptions;

  public rows: Array<any> = [];

  constructor(
    private changeDetectorRef: ChangeDetectorRef,
    public datagridService: DatagridService
  ) {
  }

  public ngOnInit() {
    this.configureDatagridService();
  }

  public configureDatagridService(): void {
    this.datagridService.httpOptions = this.httpOptions;
  }

  public ngAfterViewInit() {
    this.fetchBy(undefined);
  }

  public onSelectedRows(rows: Array<Row<Product>>): void {
    console.log(rows);
  }

  public onEditInline(editInline: EditCell): void {
    editInline.feedbackSubject.next(true);
  }

  public onPaginationChanged(pagination: Pagination): void {
    this.fetchBy(pagination);
  }

  public fetchBy(params: any): void {
    const subscription = this.datagridService
      .fetchBy(params)
      .subscribe(result => {
        console.log(result);
        this.rows = result;
        this.updateView();
        subscription.unsubscribe();
      });
  }

  private updateView(): void {
    this.changeDetectorRef.detectChanges();
  }

}

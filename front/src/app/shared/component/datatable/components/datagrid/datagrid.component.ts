import {AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {Column} from '../../model/column.model';
import {Row} from '../../model/row.model';
import {Product} from '../../../../../core/web-api/endpoints/products/product.model';
import {EditCell} from '../../model/edit-cell.model';
import {Pagination} from '../../model/pagination.model';
import {HttpOptions} from '../../model/http-options.model';
import {DatagridService} from '../../service/datagrid.service';
import {QueryBuilder} from '../../../../../core/web-api/model/query/query-builder.model';

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

  public currentPage = 1;

  public total = 0;

  public itemsPerPage = 10;

  public rows: Array<any> = [];

  public isLoading = false;

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
    this.fetchBy(this.datagridService.getQueryBuilder());
  }

  public onSelectedRows(rows: Array<Row<Product>>): void {
    console.log(rows);
  }

  public onEditInline(editInline: EditCell): void {
    editInline.feedbackSubject.next(true);
  }

  public onPaginationChanged(pagination: Pagination): void {
    const queryBuilder = this.datagridService
      .getQueryBuilder()
      .setPage(pagination.currentPage)
      .setLimit(pagination.itemsPerPage);

    this.fetchBy(queryBuilder);
  }

  public fetchBy(queryBuilder: QueryBuilder): void {
    this.isLoading = true;
    const subscription = this.datagridService
      .fetchBy(queryBuilder)
      .subscribe(resultSet => {
          const {paginationMetadata} = resultSet;
          this.rows = resultSet.data;
          this.currentPage = paginationMetadata.page;
          this.total = paginationMetadata.total;
          this.itemsPerPage = paginationMetadata.itemsPerPage;
          this.isLoading = false;
          this.updateView();
          subscription.unsubscribe();
        },
        (error: any) => {
          console.log(error);
          this.isLoading = false;
        }
      );
  }

  private updateView(): void {
    this.changeDetectorRef.detectChanges();
  }

  public onReload(): void {
    this.isLoading = true;
    this.fetchBy(this.datagridService.getQueryBuilder());
  }

}

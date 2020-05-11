import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ProductsHttpService} from '../../../../core/web-api/endpoints/products/products-http.service';
import {Product} from '../../../../core/web-api/endpoints/products/product.model';
import {CartsService} from '../../../../shared/component/cart/service/carts.service';
import {Column} from '../../../../shared/component/datatable/model/column.model';
import {ColumnTypeEnum} from '../../../../shared/component/datatable/model/column-type.enum';
import {HttpOptions} from '../../../../shared/component/datatable/model/http-options.model';
import {QueryBuilderService} from '../../../../core/web-api/query/query-builder.service';
import {SortTypeEnum} from '../../../../core/web-api/model/query/sort-type.enum';

@Component({
  selector: 'app-products',
  templateUrl: './products-list.component.html',
  styleUrls: [
    './products-list.component.scss'
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductsListComponent implements OnInit {

  public products: Array<Product> = [];

  public productsColumns: Array<Column> = [
    {
      key: '#',
      value: 'id',
      order: 0,
      type: ColumnTypeEnum.NUMBER,
      isEditable: true
    },
    {
      key: 'SKU',
      value: 'sku',
      order: 1,
      type: ColumnTypeEnum.TEXT,
      isEditable: true
    },
    {
      key: 'Name',
      value: 'name',
      order: 2,
      type: ColumnTypeEnum.TEXT,
      isEditable: true
    },
    {
      key: 'Description',
      value: 'description',
      order: 3,
      type: ColumnTypeEnum.LONG_TEXT,
      isEditable: true
    },
  ];

  public httpOptions: HttpOptions;

  constructor(
    public productsHttpService: ProductsHttpService,
    public cartsService: CartsService
  ) {
    this.configHttpOptions();
  }

  public ngOnInit(): void {

    const query = new QueryBuilderService();

    query
      .setFields([
        'teste'
      ])
      .setFilters([
        {
          key: 'teste2',
          value: 'teste'
        }
      ])
      .setSort([
        {
          key: 'teste',
          value: SortTypeEnum.ASC
        },
        {
          key: 'teste2',
          value: SortTypeEnum.DESC
        }
      ])
      .setLimit(100)
      .setPage(2);

    this.productsHttpService.query(query);

  }

  private configHttpOptions(): void {
    this.httpOptions = {
      fetchAllMethod: 'by',
      service: this.productsHttpService
    };
  }

}

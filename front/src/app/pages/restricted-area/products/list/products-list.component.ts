import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ProductsHttpService} from '../../../../core/products/products-http.service';
import {Product} from '../../../../core/products/product.model';
import {CartsService} from '../../../../shared/component/cart/service/carts.service';
import {Column} from '../../../../shared/component/datatable/model/column.model';
import {Row} from '../../../../shared/component/datatable/model/row.model';
import {ColumnTypeEnum} from '../../../../shared/component/datatable/model/column-type.enum';
import {EditCell} from '../../../../shared/component/datatable/model/edit-cell.model';

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
      type: ColumnTypeEnum.NUMBER
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

  constructor(
    public productsHttpService: ProductsHttpService,
    public cartsService: CartsService,
    public changeDetectorRef: ChangeDetectorRef
  ) {
  }

  public ngOnInit(): void {
    this.fetchProducts();
  }

  //
  // public onProductAdd(product: Product): void {
  //   this.cartsService.add(product);
  //   this.updateView();
  // }
  //
  // public onProductRemoved(product: Product): void {
  //   this.cartsService.remove(product);
  //   this.updateView();
  // }

  public onSelectedRows(rows: Array<Row<Product>>): void {
    console.log(rows);
  }

  public onEditInline(editInline: EditCell): void {
    console.log(editInline);
    editInline.feedbackSubject.next(true);
  }

  private fetchProducts(): void {
    const subscription = this.productsHttpService
      .all
      .subscribe(products => {
        this.products = products;
        this.updateView();
        subscription.unsubscribe();
      });
  }

  private updateView(): void {
    this.changeDetectorRef.detectChanges();
  }

}

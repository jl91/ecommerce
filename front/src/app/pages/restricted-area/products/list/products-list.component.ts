import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ProductsHttpService} from '../../../../core/products/products-http.service';
import {Product} from '../../../../core/products/product.model';
import {CartsService} from '../../../../shared/component/cart/service/carts.service';
import {Column} from '../../../../shared/component/datatable/model/column.model';

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
      order: 0
    },
    {
      key: 'SKU',
      value: 'sku',
      order: 1
    },
    {
      key: 'Name',
      value: 'name',
      order: 2
    },
    {
      key: 'Description',
      value: 'description',
      order: 3
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

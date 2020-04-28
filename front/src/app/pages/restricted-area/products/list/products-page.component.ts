import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ProductsHttpService} from '../../../../core/products/products-http.service';
import {Product} from '../../../../core/products/product.model';
import {CartsService} from '../../../../shared/component/cart/service/carts.service';

@Component({
  selector: 'app-products',
  templateUrl: './products-page.component.html',
  styleUrls: [
    './products-page.component.scss'
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductsPageComponent implements OnInit {

  public products: Array<Product> = [];

  constructor(
    public productsHttpService: ProductsHttpService,
    public cartsService: CartsService,
    public changeDetectorRef: ChangeDetectorRef
  ) {
  }

  public ngOnInit(): void {
    this.fetchProducts();
  }

  public onProductAdd(product: Product): void {
    this.cartsService.add(product);
    this.updateView();
  }

  public onProductRemoved(product: Product): void {
    this.cartsService.remove(product);
    this.updateView();
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

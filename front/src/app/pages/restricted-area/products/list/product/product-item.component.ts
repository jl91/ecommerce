import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../../../core/web-api/endpoints/products/product.model';

@Component({
  selector: 'app-product-item',
  templateUrl: 'product-item.component.html'
})
export class ProductItemComponent implements OnInit {

  @Input()
  product: Product;

  @Output()
  productAdded: EventEmitter<Product> = new EventEmitter<Product>();

  @Output()
  productRemoved: EventEmitter<Product> = new EventEmitter<Product>();

  constructor() {
  }

  ngOnInit() {
  }

  public add(product: Product): void {
    this.productAdded.emit(product);
  }

  public remove(product: Product): void {
    this.productRemoved.emit(product);
  }

}

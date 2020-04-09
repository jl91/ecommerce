import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../../core/products/product.model';

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

  add(product: Product): void {
    this.productAdded.emit(product);
  }

  remove(product: Product): void {
    this.productRemoved.emit(product);
  }

}

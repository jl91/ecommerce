import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {CartsService} from '../service/carts.service';
import {Product} from '../../../../core/products/product.model';
import {CartItem} from '../model/cart-item.model';

@Component({
  selector: 'app-icon-cart',
  templateUrl: 'cart-icon.component.html'
})
export class CartIconComponent implements OnInit, OnDestroy {

  public cartItemsInView: Array<CartItem> = [];
  private subscriptions: Subscription = new Subscription();
  private items: Map<string, CartItem> = new Map<string, CartItem>();

  constructor(
    private cartsService: CartsService
  ) {
  }

  ngOnInit() {
    this.registerOnCartAdd();
    this.registerOnCartRemove();
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  private registerOnCartAdd(): void {

    const subscription = this.cartsService
      .addProductObservable
      .subscribe(this.add.bind(this));

    this.subscriptions.add(subscription);
  }

  private registerOnCartRemove(): void {

    const subscription = this.cartsService
      .removeProductObservable
      .subscribe(this.remove.bind(this));

    this.subscriptions.add(subscription);
  }

  private updateCartItemsView(): void {
    this.cartItemsInView = Array.from(this.items.values());
  }

  private add(product: Product): void {

    if (this.items.has(product.sku)) {
      const cartItem = this.items.get(product.sku);
      ++cartItem.quantity;
      this.items.set(product.sku, cartItem);
      return this.updateCartItemsView();
    }

    this.items.set(product.sku, {
      product,
      sku: product.sku,
      quantity: 1
    });

    this.updateCartItemsView();
  }

  private remove(product: Product): void {

    if (!this.items.has(product.sku)) {
      return;
    }

    const cartItem = this.items.get(product.sku);
    --cartItem.quantity;

    if (cartItem.quantity <= 0) {
      this.items.delete(product.sku);
      return this.updateCartItemsView();
    }

    this.items.set(product.sku, cartItem);
    this.updateCartItemsView();
  }

}

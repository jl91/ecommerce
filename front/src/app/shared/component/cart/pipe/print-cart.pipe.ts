import {Pipe, PipeTransform} from '@angular/core';
import {CartItem} from '../model/cart-item.model';

@Pipe({
  name: 'printCartItem'
})
export class PrintCartItemPipe implements PipeTransform {
  transform(value: CartItem): string {
    return [
      value.sku,
      value.product.name
    ].join(' - ');
  }
}

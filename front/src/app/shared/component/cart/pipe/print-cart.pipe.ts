import {Pipe, PipeTransform} from '@angular/core';
import {CartItem} from '../model/cart-item.model';

@Pipe({
  name: 'printCart'
})
export class PrintCartPipePipe implements PipeTransform {
  transform(value: CartItem): string {
    return [
      value.sku,
      value.product.name,
      value.quantity
    ].join('-');
  }
}

import {Product} from '../../../../core/products/product.model';

export interface CartItem {
  sku: string;
  product: Product;
  quantity: number;
}

import {Product} from '../../../../core/web-api/endpoints/products/product.model';

export interface CartItem {
  sku: string;
  product: Product;
  quantity: number;
}

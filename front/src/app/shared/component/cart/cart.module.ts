import {NgModule} from '@angular/core';
import {CartIconComponent} from './cart-icon/cart-icon.component';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {CommonModule} from '@angular/common';
import {MatMenuModule} from '@angular/material/menu';
import {PrintCartItemPipe} from './pipe/print-cart.pipe';
import {CartsService} from './service/carts.service';
import {MatButtonModule} from '@angular/material/button';
import {MatBadgeModule} from '@angular/material/badge';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatMenuModule,
    MatBadgeModule,
  ],
  exports: [
    CartIconComponent
  ],
  declarations: [
    CartIconComponent,
    PrintCartItemPipe
  ],
  providers: [
    CartsService
  ],
})
export class CartModule {
}

import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductItemComponent} from './product/product-item.component';
import {MatCardModule} from '@angular/material/card';
import {ProductsPageComponent} from './products-page.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  exports: [
    ProductsPageComponent,
    ProductItemComponent
  ],
  declarations: [
    ProductsPageComponent,
    ProductItemComponent
  ],
  providers: [],
})
export class ProductsModule {
}

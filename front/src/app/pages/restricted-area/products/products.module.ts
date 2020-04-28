import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductItemComponent} from './list/product/product-item.component';
import {MatCardModule} from '@angular/material/card';
import {ProductsPageComponent} from './list/products-page.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from "@angular/material/grid-list";

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule
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

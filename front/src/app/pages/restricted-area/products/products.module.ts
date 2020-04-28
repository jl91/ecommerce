import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductItemComponent} from './list/product/product-item.component';
import {MatCardModule} from '@angular/material/card';
import {ProductsPageComponent} from './list/products-page.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from '@angular/material/grid-list';
import {SearchComponent} from './list/search/search.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule
  ],
  exports: [
    ProductsPageComponent,
    ProductItemComponent,
    SearchComponent
  ],
  declarations: [
    ProductsPageComponent,
    ProductItemComponent,
    SearchComponent
  ],
  providers: [],
})
export class ProductsModule {
}

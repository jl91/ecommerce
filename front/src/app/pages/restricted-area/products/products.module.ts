import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductItemComponent} from './list/product/product-item.component';
import {MatCardModule} from '@angular/material/card';
import {ProductsListComponent} from './list/products-list.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatGridListModule} from '@angular/material/grid-list';
import {SearchComponent} from './list/search/search.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {SaveComponent} from './save/save.component';
import {ProductForm} from "./save/product.form";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  exports: [
    ProductsListComponent,
    ProductItemComponent,
    SearchComponent
  ],
  declarations: [
    ProductsListComponent,
    ProductItemComponent,
    SearchComponent,
    SaveComponent
  ],
  providers: [
    ProductForm
  ],
})
export class ProductsModule {
}

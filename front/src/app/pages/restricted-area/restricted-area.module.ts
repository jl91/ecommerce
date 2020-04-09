import {NgModule} from '@angular/core';
import {LayoutModule} from '../../shared/component/layout/layout.module';
import {RestrictedAreaRountingModule} from './restricted-area-rounting.module';
import {RestrictedAreaComponent} from './restricted-area.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../../shared/shared.module';
import {MatCardModule} from '@angular/material/card';
import {ProductsModule} from './products/products.module';
import {CustomersModule} from './customers/customers.module';
import {HomeModule} from './home/home.module';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    LayoutModule,
    RestrictedAreaRountingModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    ProductsModule,
    ProductsModule,
    CustomersModule,
  ],
  exports: [
    ProductsModule,
    CustomersModule,
    HomeModule,
    RestrictedAreaRountingModule
  ],
  declarations: [
    RestrictedAreaComponent,
  ],
  providers: [],
})
export class RestrictedAreaModule {
}

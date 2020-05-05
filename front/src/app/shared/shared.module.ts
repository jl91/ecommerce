import {NgModule} from '@angular/core';
import {LayoutModule} from './component/layout/layout.module';
import {CartModule} from './component/cart/cart.module';
import {DatatableModule} from './component/datatable/datatable.module';

@NgModule({
  imports: [
    LayoutModule,
    CartModule,
    DatatableModule
  ],
  exports: [
    LayoutModule,
    CartModule,
    DatatableModule
  ],
  declarations: [],
  providers: [],
})
export class SharedModule {
}

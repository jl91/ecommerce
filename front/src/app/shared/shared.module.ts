import {NgModule} from '@angular/core';
import {LayoutModule} from './component/layout/layout.module';
import {CartModule} from './component/cart/cart.module';

@NgModule({
  imports: [
    LayoutModule,
    CartModule
  ],
  exports: [
    LayoutModule,
    CartModule
  ],
  declarations: [],
  providers: [],
})
export class SharedModule {
}

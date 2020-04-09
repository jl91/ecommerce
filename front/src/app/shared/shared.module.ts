import {NgModule} from '@angular/core';
import {LayoutModule} from './component/layout/layout.module';
import {MenuService} from './service/menu.service';
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
  providers: [
    MenuService,
  ],
})
export class SharedModule {
}

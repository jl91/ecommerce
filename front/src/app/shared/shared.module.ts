import {NgModule} from '@angular/core';
import {LayoutModule} from "./component/layout/layout.module";
import {MenuService} from "./service/menu.service";

@NgModule({
  imports: [
    LayoutModule
  ],
  exports: [
    LayoutModule
  ],
  declarations: [],
  providers: [
    MenuService
  ],
})
export class SharedModule {
}

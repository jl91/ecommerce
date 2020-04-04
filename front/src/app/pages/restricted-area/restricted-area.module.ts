import {NgModule} from '@angular/core';
import {HomeComponent} from "./home/home.component";
import {ProductsComponent} from "./products/products.component";
import {CustomersComponent} from "./customers/customers.component";
import {LayoutModule} from "../../shared/component/layout/layout.module";
import {RestrictedAreaRountingModule} from "./restricted-area-rounting.module";
import {RestrictedAreaComponent} from "./restricted-area.component";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../../shared/shared.module";
import {MatCardModule} from "@angular/material/card";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    LayoutModule,
    RestrictedAreaRountingModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule
  ],
  exports: [
    CustomersComponent,
    HomeComponent,
    ProductsComponent,
    RestrictedAreaRountingModule
  ],
  declarations: [
    RestrictedAreaComponent,
    CustomersComponent,
    HomeComponent,
    ProductsComponent
  ],
  providers: [],
})
export class RestrictedAreaModule {
}

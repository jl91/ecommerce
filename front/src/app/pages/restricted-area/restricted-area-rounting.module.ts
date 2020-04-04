import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CustomersComponent} from "./customers/customers.component";
import {ProductsComponent} from "./products/products.component";
import {RestrictedAreaComponent} from "./restricted-area.component";


const routes: Routes = [
  {
    path: '',
    component: RestrictedAreaComponent,
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'customer',
        component: CustomersComponent
      },
      {
        path: 'products',
        component: ProductsComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestrictedAreaRountingModule {
}

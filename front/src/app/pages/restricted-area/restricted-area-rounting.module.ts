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
        component: HomeComponent,
        data: {
          title: "Home"
        }
      },
      {
        path: 'customer',
        component: CustomersComponent,
        data: {
          title: "Customers"
        }
      },
      {
        path: 'products',
        component: ProductsComponent,
        data: {
          title: "Products"
        }
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

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {CustomersComponent} from './customers/customers.component';
import {ProductsPageComponent} from './products/list/products-page.component';
import {RestrictedAreaComponent} from './restricted-area.component';


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
          title: 'Home'
        }
      },
      {
        path: 'customer',
        component: CustomersComponent,
        data: {
          title: 'Customers'
        }
      },
      {
        path: 'products',
        component: ProductsPageComponent,
        data: {
          title: 'Products'
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

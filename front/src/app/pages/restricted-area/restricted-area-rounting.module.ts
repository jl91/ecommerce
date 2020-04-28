import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {CustomersComponent} from './customers/customers.component';
import {ProductsListComponent} from './products/list/products-list.component';
import {RestrictedAreaComponent} from './restricted-area.component';
import {SaveComponent} from "./products/save/save.component";


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
        component: ProductsListComponent,
        data: {
          title: 'Products'
        },
      },
      {
        path: 'products/create',
        component: SaveComponent,
        data: {
          title: 'Add Product'
        },
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
